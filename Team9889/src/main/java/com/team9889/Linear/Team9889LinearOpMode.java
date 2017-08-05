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
    private Team9889LinearOpMode InternalopMode = null;
    private ElapsedTime period = new ElapsedTime();

    protected void waitForTeamStart(Team9889LinearOpMode opMode){
        this.InternalopMode = opMode;

        if (isCameraAvailable()){
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

    /**
     * @param action All are defined in action folder
     */
    protected void runAction(Action action){
        //If there is an error, Stop the OpMode
        boolean error = false;
        try {
            action.start(this.InternalopMode);
            this.updateTelemetry();
        } catch (Exception e){
            this.InternalopMode.telemetry.addData("Error in Starting Action", action);
            this.InternalopMode.telemetry.update();
        }

        while (!action.isFinished() && this.InternalopMode.opModeIsActive() && !error){
            try {
                action.update(this.InternalopMode);
                this.updateTelemetry();
            } catch (Exception e){
                this.InternalopMode.telemetry.addData("Error in Updating Action", action);
                this.InternalopMode.telemetry.update();
            }
        }

        if (this.InternalopMode.opModeIsActive() && !error){
            try {
                action.done();
                this.updateTelemetry();
            } catch (Exception e){
                this.InternalopMode.telemetry.addData("Error in Finishing Action", action);
                this.InternalopMode.telemetry.update();
            }
        }

        this.InternalopMode.telemetry.addData("Finished Action", "");
        this.InternalopMode.telemetry.update();

        if(!error){
            this.mSuperstructure.stop();
            this.InternalopMode.sleep(5000);
        }
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
