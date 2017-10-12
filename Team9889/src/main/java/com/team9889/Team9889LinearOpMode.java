package com.team9889;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.ReadWriteFile;
import com.team9889.lib.AutoTransitioner;
import com.team9889.lib.VuMark;
import com.team9889.subsystems.Robot;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

/**
 * Created by joshua9889 on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {

    //New Robot
    public Robot Robot = com.team9889.subsystems.Robot.getInstance();

    //New Driver Station
    public Driver_Station driver_station = new Driver_Station();

    //Used to reference the main opmode in this class
    public Team9889LinearOpMode InternalopMode = null;

    //For Vuforia
    private VuMark vuMark = new VuMark();

    private ElapsedTime period = new ElapsedTime();

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
            //Auto Transitioning
            AutoTransitioner.transitionOnStop(this.InternalopMode, "Teleop");

            //Autonomous Settings
            this.InternalopMode.getAutonomousPrefs();

            //Vuforia
            //Uses the camera on the screen side
            this.vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);
            while(!isStarted()){
                this.vuMark.updateTarget(this);
                this.InternalopMode.telemetry.addData("Ready to Start", "");
                this.InternalopMode.telemetry.addData("VuMark", "%s visible", this.vuMark.getOuputVuMark());
                this.InternalopMode.telemetry.addData("","-----------------------");
                this.InternalopMode.telemetry.addData("Alliance", alliance);
                this.InternalopMode.telemetry.addData("Front or Back", frontBack);
                this.InternalopMode.telemetry.addData("","-----------------------");
                this.InternalopMode.telemetry.addData("Pickup from Glyph Pit", getPitGlyph);
                this.InternalopMode.telemetry.addData("Pickup Alliance Partner's Glyph", getPartnerGlyph);
                this.InternalopMode.telemetry.update();
                idle();
            }
        }

        this.InternalopMode.telemetry.addData("Waiting for Start", "");
        this.InternalopMode.telemetry.update();
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
        this.Robot.outputToTelemetry(this.InternalopMode);
        this.InternalopMode.telemetry.update();
    }

    //Final Action to be run
    protected void finalAction(){
        try {
            this.Robot.stop();
        } catch (Exception e){}

        this.InternalopMode.requestOpModeStop();
    }

    //Built-in function by FIRST.
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
