package com.team9889;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.lib.AutoTransitioner;
import com.team9889.lib.VuMark;
import com.team9889.subsystems.Superstructure;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by joshua9889 on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {

    //New Robot
    public Superstructure mSuperstructure = Superstructure.getInstance();

    //New Driver Station
    Driver_Station driver_station = new Driver_Station();

    //Used to reference the main opmode in this class
    public Team9889LinearOpMode InternalopMode = null;

    //For Vuforia
    private VuMark vuMark = new VuMark();
    private ElapsedTime period = new ElapsedTime();

    //Match settings
    public String alliance, frontBack;
    public boolean getPartnerGlyph;

    /**
     * Use this method instead of waitForStart.
     * @param opMode The Team9889LinearOpMode reference
     * @param autonomous If the OpMode is an autonomous or not
     */
    protected void waitForTeamStart(Team9889LinearOpMode opMode, boolean autonomous){
        this.InternalopMode = opMode;

        //this.mSuperstructure.init(this.InternalopMode, false);
        //this.updateTelemetry();

        if(autonomous){
            //Auto Transitioning
            AutoTransitioner.transitionOnStop(this.InternalopMode, "Teleop");

            //Autonomous Settings
            this.InternalopMode.getAutonomousPrefs();
            this.InternalopMode.telemetry.addData("Ready to Start", "");
            this.InternalopMode.telemetry.addData("Alliance", alliance);
            this.InternalopMode.telemetry.addData("Front or Back", frontBack);
            this.InternalopMode.telemetry.addData("Pickup", getPartnerGlyph);
            this.InternalopMode.telemetry.update();

            //Vuforia
            //Uses the camera on the screen side
            this.vuMark.setup(this.InternalopMode, VuforiaLocalizer.CameraDirection.FRONT);
            while(!isStarted()){
                this.vuMark.updateTarget(this);
                idle();
            }
        }


        //Wait for DS start
        this.InternalopMode.waitForStart();

        if(autonomous)
            this.vuMark.disableVuforia();
    }

    /**
     * Run this to update the Default Telemetry
     */
    public void updateTelemetry(){
        this.InternalopMode.telemetry.addData("Runtime> ", this.InternalopMode.getRuntime());
        this.mSuperstructure.outputToTelemetry(this.InternalopMode);
        this.InternalopMode.telemetry.update();
    }

    //Final Action to be run
    protected void finalAction(){
        try {
            this.mSuperstructure.stop();
        } catch (Exception e){}

        this.InternalopMode.requestOpModeStop();
    }

    //Built-in function by FIRST. Put in all loops
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
    }

    //What column to score the glyph in
    public RelicRecoveryVuMark WhatColumnToScoreIn(){
        return vuMark.getOuputVuMark();
    }
}
