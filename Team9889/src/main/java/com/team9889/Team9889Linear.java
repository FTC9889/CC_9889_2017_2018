package com.team9889;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.provider.Settings;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.lib.AutoTransitioner;
import com.team9889.lib.VuMark;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Robot;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import camera_opmodes.LinearOpModeCamera;

/**
 * Created by joshua9889 on 4/17/17.
 */

public abstract class Team9889Linear extends LinearOpMode {

    //New Robot
    public Robot Robot = com.team9889.subsystems.Robot.getInstance();
    private static boolean init = false;

    //New Driver Station
    public Driver_Station driver_station = new Driver_Station();

    //Used to reference the main opmode in this class
    public Team9889Linear InternalopMode = null;

    //For VuMark
    private VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);

    private RelicRecoveryVuMark currentVumark = RelicRecoveryVuMark.UNKNOWN;

    public enum JewelColor {
        Red, Blue
    }
    public JewelColor jewel_Color = null;
    private int redVotes, blueVotes = 0;

    public ElapsedTime timeToCollect = new ElapsedTime();

    //Match settings
    public String alliance, frontBack;
    public boolean getPartnerGlyph, getPitGlyph;

    protected void waitForStart(Team9889Linear opMode, boolean autonomous){
        waitForStart(opMode, autonomous, autonomous);
    }

    /**
     * Use this method instead of waitForStart.
     * @param opMode The Team9889Linear reference
     * @param autonomous If the OpMode is an autonomous or not
     */
    protected void waitForStart(Team9889Linear opMode, final boolean autonomous, boolean runCamera){
        // Will it throw a null pointer exception???
        Robot.init(this, autonomous);

        InternalopMode = opMode;

        // Start of Auto Code for Camera and the like
        if(autonomous && runCamera){
            //Auto Transitioning
            // Will it throw a null pointer exception???
            AutoTransitioner.transitionOnStop(this, "Teleop");

            //Autonomous Settings
            getAutonomousPrefs();

            // Vuforia setup
            vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);

            // Scanning Code
            while(!isStarted() && !isStopRequested()){
                vuMark.update(opMode);

                if (vuMark.getOuputVuMark() != RelicRecoveryVuMark.UNKNOWN)
                    currentVumark = vuMark.getOuputVuMark();

                if(redVotes>500 || blueVotes>500){
                    redVotes = 0;
                    blueVotes = 0;
                }

                int redValue = 0;
                int blueValue = 0;

                Bitmap bm = vuMark.getBm();
                if(bm != null){
                    for (int x=(int)(bm.getWidth()/0.75); x < bm.getWidth(); x++) {
                        for (int y = 0; y < bm.getHeight()/6; y++) {
                            int pixel = bm.getPixel(x, y);
                            redValue += vuMark.red(pixel);
                            blueValue += vuMark.blue(pixel);
                        }
                    }
                }

                // Single votes
                if (redValue > blueValue)
                    redVotes++;
                else if(blueValue > redValue)
                    blueVotes++;

                if(redVotes > blueVotes)
                    jewel_Color = JewelColor.Red;
                else
                    jewel_Color = JewelColor.Blue;

                telemetry.addData("Robot Init", init);
                telemetry.addData("VuMark", WhatColumnToScoreIn());
                telemetry.addData("Color", jewel_Color);
                telemetry.addData("Red", redValue);
                telemetry.addData("Blue", blueValue);
                telemetry.update();
            }// End of Scanning Code
            timeToCollect.reset();

        }// End of Auto Code for Camera and the like1
        else{
            driver_station.init(opMode); // New Driver station
            while (!isStarted()){
                telemetry.addData("Waiting for Start", "");
                telemetry.update();
            }
        }

        //Wait for DS start
        this.waitForStart();
        if(!autonomous)
            this.Robot.getLift().goTo(GlyphLypht.Mode.Teleop);
    }

    /**
     * Run this to update the Default Telemetry
     */
    public void updateTelemetry(){
        telemetry.addData("Runtime", this.getRuntime());
        Robot.outputToTelemetry(this);
        telemetry.update();
    }


    /**
     * Used to stop everything.
     */
    protected void finalAction(){
        this.Robot.stop();
        this.requestOpModeStop();
    }

    //Get the Autonomous prefs from the app's activity and convert them to local variables instead.
    private void getAutonomousPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);
        alliance = preferences.getString("AllianceColor", "error");
        frontBack = preferences.getString("FrontBack", "error");
        getPartnerGlyph = preferences.getBoolean("PickupAllianceGlyph", false);
        getPitGlyph = preferences.getBoolean("PickupPitGlyph", false);
    }

    //What column to score the glyph in
    public RelicRecoveryVuMark WhatColumnToScoreIn(){
        return currentVumark;
    }

    public void print(String string){
        System.out.print(string);
    }

    public void print(double num){
        System.out.print(num);
    }
}
