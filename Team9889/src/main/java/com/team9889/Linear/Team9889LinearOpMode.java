package com.team9889.Linear;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Linear.auto.actions.Action;
import com.team9889.Linear.subsystems.*;

import camera_opmodes.LinearOpModeCamera;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {

    public Superstructure mSuperstructure = Superstructure.getInstance();
    public Team9889LinearOpMode InternalopMode = null;
    private ElapsedTime period = new ElapsedTime();

    //Match settings
    public String alliance;

    protected void waitForTeamStart(Team9889LinearOpMode opMode){
        this.InternalopMode = opMode;

        //Autonomous Settings
        this.InternalopMode.getAutonomousPrefs();

        //this.mSuperstructure.init(this.InternalopMode, false);

        this.InternalopMode.telemetry.addData("Ready to Start", "");
        this.InternalopMode.telemetry.addData("Alliance", alliance);
        //this.updateTelemetry();
        this.InternalopMode.telemetry.update();

        //Wait for DS start
        this.InternalopMode.waitForStart();
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

    private void getAutonomousPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);
        alliance = preferences.getString("Alliance Color", "error");
    }
}
