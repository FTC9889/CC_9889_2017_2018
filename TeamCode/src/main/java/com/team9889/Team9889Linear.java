package com.team9889;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.lib.AutoTransitioner;
import com.team9889.lib.VuMark;
import com.team9889.subsystems.Robot;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static com.team9889.lib.VuMark.blue;
import static com.team9889.lib.VuMark.red;

/**
 * This class extends LinearOpMode and makes it
 * easier to make code for the robot and not copy
 * and pasting init code.
 *
 * Created by joshua9889 on 4/17/17.
 */

public abstract class Team9889Linear extends LinearOpMode {

    // New Robot
    public Robot Robot = com.team9889.subsystems.Robot.getInstance();

    // New Driver Station
    Driver_Station driver_station = new Driver_Station();

    // Used to reference the main opmode in this class
    public Team9889Linear InternalopMode = this;

    // For VuMark
    private VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);

    // Jewel
    public enum JewelColor {
        Red, Blue
    }
    public JewelColor jewel_Color = null;
    private int redVotes, blueVotes = 0;

    RelicRecoveryVuMark test = RelicRecoveryVuMark.UNKNOWN;

    // Match settings
    protected String alliance, frontBack;
    protected boolean getPartnerGlyph, getPitGlyph;

    public void waitForStart(boolean autonomous){
        waitForStart(autonomous, autonomous);
    }

    /**
     * Use this method instead of waitForStart.
     * @param autonomous If the OpMode is an autonomous or not
     */
    public void waitForStart(final boolean autonomous, boolean runCamera){
        // Will it throw a null pointer exception???
        Robot.init(this, autonomous);

        telemetry.setMsTransmissionInterval(autonomous ? 50:1000);

        // Start of Auto Code for Camera and the like
        if(autonomous && runCamera){
            //Auto Transitioning
            // Will it throw a null pointer exception???
            AutoTransitioner.transitionOnStop(this, "Teleop");

            //Autonomous Settings
            getAutonomousPrefs();

            // Setup Vuforia
            vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT, false);
            ElapsedTime t = new ElapsedTime();

            while(isInInitLoop()) {
                // Update VuMark
                vuMark.update();

                if(vuMark.getOuputVuMark()!= RelicRecoveryVuMark.UNKNOWN)
                    test = vuMark.getOuputVuMark();

                // Value of all pixels
                int redValue = 0;
                int blueValue = 0;

                // Get current bitmap from Vuforia
                Bitmap bm = vuMark.getBm(20);

                if(bm != null){
                    // Scan area for red and blue pixels
                    for (int x = 0; x < bm.getWidth()/5 && isInInitLoop(); x++) {
                        for (int y = (bm.getHeight()/4)+(bm.getHeight()/2); y < bm.getHeight() && isInInitLoop(); y++) {
                            int pixel = bm.getPixel(x,y);
                            redValue += red(pixel);
                            blueValue += blue(pixel);

                            if(redValue>blueValue)
                                redVotes++;
                            else if(blueValue>redValue)
                                blueVotes++;
                            idle();
                        }
                    }
                    bm.recycle();
                }

                // Cut off
                if (redVotes>300) {
                    redVotes = 50;
                    blueVotes = 0;
                } else if(blueVotes > 300){
                    redVotes = 0;
                    blueVotes = 50;
                }

                // Calculate FPS
                double fps = 1/t.seconds();
                t.reset();

                // Output Telemetry
                telemetry.addData("FPS", fps);
                telemetry.addData("VuMark", WhatColumnToScoreIn());
                if(jewel_Color != null)
                    telemetry.addData("Color", jewel_Color.toString());
                telemetry.addData("Red Votes", redVotes);
                telemetry.addData("Blue Votes", blueVotes);
                telemetry.update();
                idle();
            }

            // Voting system
            if(redVotes>blueVotes)
                jewel_Color = JewelColor.Red;
            else if(blueVotes>redVotes)
                jewel_Color = JewelColor.Blue;

        }// End of Auto Code for Camera and the like
        else{
            driver_station.init(this); // New Driver station
            while (isInInitLoop()){
                telemetry.addData("Waiting for Start", "");
                telemetry.update();
            }
        }
    }

    /**
     * Run this to update the Default Telemetry
     */
    protected void updateTelemetry(){
        telemetry.addData("Runtime", this.getRuntime());
        Robot.outputToTelemetry(this);
        telemetry.update();
    }


    /**
     * Used to stop everything.
     */
    protected void finalAction(){
        Robot.stop();
        requestOpModeStop();
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
    protected RelicRecoveryVuMark WhatColumnToScoreIn(){
        return test;
    }

    protected boolean isInInitLoop(){
        return !isStarted() && !isStopRequested();
    }
}