package com.team9889;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

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

public abstract class Team9889LinearOpMode extends LinearOpModeCamera {

    //New Robot
    public Robot Robot = com.team9889.subsystems.Robot.getInstance();

    //New Driver Station
    public Driver_Station driver_station = new Driver_Station();

    //Used to reference the main opmode in this class
    public Team9889LinearOpMode InternalopMode = null;

    //For VuMark
    private VuMark vuMark = new VuMark();

    private boolean runVuforia = false;

    //Used for camera init
    private boolean first = true;

    public enum JewelColor {
        Red, Blue
    }
    public JewelColor jewel_Color = null;
    private int redVotes, blueVotes = 0;

    //Match settings
    public String alliance, frontBack;
    public boolean getPartnerGlyph, getPitGlyph;

    /**
     * Use this method instead of waitForStart.
     * @param opMode The Team9889LinearOpMode reference
     * @param autonomous If the OpMode is an autonomous or not
     */
    protected void waitForTeamStart(Team9889LinearOpMode opMode, boolean autonomous){
        this.InternalopMode = opMode;

        try {
            this.Robot.init(this.InternalopMode, false);
            this.updateTelemetry();
        } catch (Exception e){
            this.InternalopMode.telemetry.addData("No Hardware attached", "");
            this.InternalopMode.telemetry.update();
        }


        if(autonomous){
            this.Robot.getLift().goTo(GlyphLypht.Mode.Auto);

            //Auto Transitioning
            AutoTransitioner.transitionOnStop(this.InternalopMode, "Teleop");

            //Autonomous Settings
            this.InternalopMode.getAutonomousPrefs();

            ElapsedTime vuforiaTimer = new ElapsedTime();
            ElapsedTime cameraTimer = new ElapsedTime();

            //VuMark
            //Uses the camera on the screen side
            if (runVuforia)
                this.vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);

            while(!isStarted()){
                //Print the auto settings

                // Vuforia Loop
                if (runVuforia) {
                    vuforiaTimer.reset();
                    while (vuforiaTimer.milliseconds() < 4000 && !isStarted()) {
                        telemetry.addData("VuMark", vuMark.getOuputVuMark());
                        this.InternalopMode.telemetry.update();

                        if (vuMark.getOuputVuMark() == RelicRecoveryVuMark.UNKNOWN) {
                            this.vuMark.
                                    updateTarget(this);
                        } else {
                            this.vuMark.closeVuforia();
                            break;
                        }
                        idle();
                    }
                }


                // Camera Loop
                cameraTimer.reset();
                redVotes = 0;
                blueVotes = 0;
                while(cameraTimer.milliseconds() < 2000 && !isStarted()){
                    //Then get the jewel color
                    try {
                        setCameraDownsampling(8);

                        //Init camera once
                        if (first) {
                            Thread startCamera = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    startCamera();  // can take a while.
                                    // best started before waitForStart
                                    telemetry.addLine("Camera ready!");
                                    telemetry.update();
                                }
                            });
                            startCamera.run();

                            first = false;
                        }

                        int redValue = 0;
                        int blueValue = 0;

                        // get image, rotated so (0,0) is in the bottom left of the preview window
                        Bitmap rgbImage;
                        rgbImage = convertYuvImageToRgb(yuvImage, width, height, 2);

                        for (int x =0; x < rgbImage.getWidth()/4; x++) {
                            for (int y = 0; y < rgbImage.getHeight()/6; y++) {
                                int pixel = rgbImage.getPixel(x, y);
                                redValue += red(pixel);
                                blueValue += blue(pixel);
                            }
                        }

                        if (redValue > blueValue)
                            redVotes++;
                        else
                            blueVotes++;

                        telemetry.addData("Red", redVotes);
                        telemetry.addData("Blue", blueVotes);
                        telemetry.update();

                        if (redVotes > 500 || blueVotes >500)
                            break;

                    } catch (Exception e) {}

                    idle();
                }
                stopCamera();
            }


        }
        this.InternalopMode.telemetry.addData("Waiting for Start", "");
        this.InternalopMode.telemetry.update();

        //Wait for DS start
        this.InternalopMode.waitForStart();

        if (redVotes > blueVotes)
            jewel_Color = JewelColor.Red;
        else
            jewel_Color = JewelColor.Blue;
    }

    /**
     * Run this to update the Default Telemetry
     */
    public void updateTelemetry(){
        this.InternalopMode.telemetry.addData("Runtime> ", this.InternalopMode.getRuntime());
        this.Robot.outputToTelemetry(this.InternalopMode);
        this.InternalopMode.telemetry.update();
    }


    /**
     * Used to stop everything.
     */
    protected void finalAction(){
        try {
            this.Robot.stop();
        } catch (Exception e){}

        this.InternalopMode.requestOpModeStop();
    }

    //Built-in function by FIRST. Used for things
    private ElapsedTime period = new ElapsedTime();
    protected void waitForTick(long periodMs) {
        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
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
        return vuMark.getOuputVuMark();
    }
}
