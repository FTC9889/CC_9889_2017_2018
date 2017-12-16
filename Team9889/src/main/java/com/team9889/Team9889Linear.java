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

public abstract class Team9889Linear extends LinearOpModeCamera {

    //New Robot
    public Robot Robot = com.team9889.subsystems.Robot.getInstance();

    //New Driver Station
    public Driver_Station driver_station = new Driver_Station();

    //Used to reference the main opmode in this class
    public Team9889Linear InternalopMode = null;

    //For VuMark
    private VuMark vuMark = new VuMark();

    private boolean runVuforia = true;
    private boolean runCamera = true;

    private RelicRecoveryVuMark currentVumark = RelicRecoveryVuMark.UNKNOWN;
    private RelicRecoveryVuMark lastVumark = RelicRecoveryVuMark.UNKNOWN;

    //Used for camera init
    private boolean first = true;

    public enum JewelColor {
        Red, Blue
    }
    public JewelColor jewel_Color = null;
    private int redVotes, blueVotes = 0;

    public ElapsedTime timeToCollect = new ElapsedTime();

    //Match settings
    public String alliance, frontBack;
    public boolean getPartnerGlyph, getPitGlyph;

    /**
     * Use this method instead of waitForStart.
     * @param opMode The Team9889Linear reference
     * @param autonomous If the OpMode is an autonomous or not
     */
    protected void waitForStart(Team9889Linear opMode, boolean autonomous){
        this.InternalopMode = opMode;

        this.Robot.init(this.InternalopMode, autonomous);

        // Start of Auto Code for Camera and the like
        if(autonomous){
            //Auto Transitioning
            AutoTransitioner.transitionOnStop(this.InternalopMode, "Teleop");

            //Autonomous Settings
            this.InternalopMode.getAutonomousPrefs();

            // Timeout stuff
            ElapsedTime vuforiaTimer = new ElapsedTime();
            ElapsedTime cameraTimer = new ElapsedTime();

            // It's a weird world
            try{
                stopCamera();
            } catch (Exception e){}

            try{
                vuMark.closeVuforia();
            } catch (Exception e){}

            // Start of Scanning Code
            while(!isStarted()){

                // Start of Vuforia Code
                if (runVuforia && !opMode.isStarted()) {
                    // Reset Timer
                    vuforiaTimer.reset();
                    boolean runningVuforia = true;

                    boolean thingForCheckingIfCameraWorks = true;
                    while(thingForCheckingIfCameraWorks && !isStarted()){
                        try {
                            this.stopCamera();
                            sleep(10);
                        } catch (Exception e){}

                        try {
                            this.vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);
                            thingForCheckingIfCameraWorks = false;
                        } catch (Exception e){
                            thingForCheckingIfCameraWorks = true;
                        }
                    }

                    telemetry.addData("Running Vuforia","");
                    telemetry.addData("Alliance Color", alliance);
                    telemetry.addData("Stone", frontBack);
                    telemetry.addData("Pickup Partner's Glyph", getPartnerGlyph);
                    telemetry.addData("Go to PIT?", getPitGlyph);
                    telemetry.update();

                    //Scan Image
                    while(vuforiaTimer.milliseconds() < 4000 && !isStarted() && runningVuforia){
                        if (vuMark.getOuputVuMark() == RelicRecoveryVuMark.UNKNOWN) {
                            this.vuMark.updateTarget(this);
                            currentVumark = lastVumark;
                        } else {
                            currentVumark = vuMark.getOuputVuMark();
                            if(currentVumark != RelicRecoveryVuMark.UNKNOWN)
                                lastVumark = currentVumark;
                            this.vuMark.closeVuforia();
                            runningVuforia = false;
                        }

                        telemetry.addData("Vuforia", vuMark.getOuputVuMark());
                        telemetry.addData("Alliance Color", alliance);
                        telemetry.addData("Stone", frontBack);
                        telemetry.addData("Pickup Partner's Glyph", getPartnerGlyph);
                        telemetry.addData("Go to PIT?", getPitGlyph);
                        telemetry.update();

                        idle();
                    }
                    lastVumark = this.vuMark.getOuputVuMark();
                }// End of Vuforia Code

                // Start of Camera Code
                if(runCamera&& !opMode.isStarted()){
                    cameraTimer.reset();
                    boolean runningCamera = true;

                    telemetry.addData("Starting Camera","");
                    telemetry.addData("Alliance Color", alliance);
                    telemetry.addData("Stone", frontBack);
                    telemetry.addData("Pickup Partner's Glyph", getPartnerGlyph);
                    telemetry.addData("Go to PIT?", getPitGlyph);
                    telemetry.update();
                    boolean thingForCheckingIfCameraWorks = true;
                    while(thingForCheckingIfCameraWorks && !isStarted()){
                        try {
                            this.vuMark.closeVuforia();
                            sleep(10);
                        } catch (Exception e){}

                        try {
                            setCameraDownsampling(8);
                            startCamera();
                            thingForCheckingIfCameraWorks = false;
                        } catch (Exception e){
                            thingForCheckingIfCameraWorks = true;
                        }
                    }

                    telemetry.addData("Running Camera","");
                    telemetry.update();
                    sleep(1000);

                    while (cameraTimer.milliseconds() < 4000 && !isStarted() && runningCamera){
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

                        // Single votes
                        if (redValue > blueValue)
                            redVotes++;
                        else
                            blueVotes++;

                        // Store the color of jewel
                        if (redVotes > blueVotes)
                            jewel_Color = JewelColor.Red;
                        else
                            jewel_Color = JewelColor.Blue;

                        // Exit the loop when one of the votes is more then 500
                        if (redVotes > 500 || blueVotes >500){
                            first = true;
                            runningCamera = false;
                            redVotes = 0;
                            blueVotes = 0;
                            stopCamera();
                            sleep(10);
                        }

                        // Output Telemetry
                        telemetry.addData("Red", redVotes);
                        telemetry.addData("Blue", blueVotes);
                        //telemetry.addData("Vuforia", vuMark.getOuputVuMark());
                        telemetry.addData("Alliance Color", alliance);
                        telemetry.addData("Stone", frontBack);
                        telemetry.addData("Pickup Partner's Glyph", getPartnerGlyph);
                        telemetry.addData("Go to PIT?", getPitGlyph);
                        telemetry.update();

                        idle();
                    }
                }// End of Camera Code

            }// End of Scanning Code
            try{
                stopCamera();
            } catch (Exception e){}

            try{
                vuMark.closeVuforia();
            } catch (Exception e){}

            timeToCollect.reset();

        }// End of Auto Code for Camera and the like
        else{
            driver_station.init(InternalopMode); // New Driver station
            this.InternalopMode.telemetry.addData("Waiting for Start", "");
            this.InternalopMode.telemetry.update();
        }

        //Wait for DS start
        this.InternalopMode.waitForStart();
        if(!autonomous)
            this.Robot.getLift().goTo(GlyphLypht.Mode.Teleop);
    }

    /**
     * Run this to update the Default Telemetry
     */
    public void updateTelemetry(){
        this.InternalopMode.telemetry.addData("Runtime", this.InternalopMode.getRuntime());
        this.Robot.outputToTelemetry(this.InternalopMode);
        this.InternalopMode.telemetry.update();
    }


    /**
     * Used to stop everything.
     */
    protected void finalAction(){
        this.Robot.stop();
        this.InternalopMode.requestOpModeStop();
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
}
