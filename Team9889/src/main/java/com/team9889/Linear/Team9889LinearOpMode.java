package com.team9889.Linear;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Linear.auto.actions.Action;
import com.team9889.Linear.subsystems.*;

import camera_opmodes.LinearOpModeCamera;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpModeCamera {

    public Superstructure mSuperstructure = Superstructure.getInstance();
    public Team9889LinearOpMode InternalopMode = null;
    public boolean UseCamera = true;
    private ElapsedTime period = new ElapsedTime();

    protected void waitForTeamStart(Team9889LinearOpMode opMode){
        this.InternalopMode = opMode;

        if (isCameraAvailable() && UseCamera){
            this.setCameraDownsampling(8);
            this.InternalopMode.telemetry.addLine("Wait for camera to finish initializing!");
            this.InternalopMode.telemetry.update();
            this.startCamera();  // can take a while.
            // best started before waitForStart
            this.InternalopMode.telemetry.addLine("Camera ready!");
            this.InternalopMode.telemetry.update();
        }

        mSuperstructure.Setup_Superstructure(this.InternalopMode);
        mSuperstructure.init(this.InternalopMode.hardwareMap, false);

        this.updateTelemetry();

        //Wait for DS start
        this.InternalopMode.waitForStart();

        this.InternalopMode.telemetry.addData("Started", "");
        this.InternalopMode.telemetry.update();
    }

    /**
     * Run this to update the Default Telemetry
     */
    public void updateTelemetry(){
        this.mSuperstructure.outputToTelemetry(this.InternalopMode);
        outputToTelemetryForCamera(this.InternalopMode);
        this.InternalopMode.telemetry.update();
    }

    //Final Action to be run
    protected void finalAction(){
        try {
            this.mSuperstructure.stop();
        } catch (Exception e){}

        stopCamera();
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

}
