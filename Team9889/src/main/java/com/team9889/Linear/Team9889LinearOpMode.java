package com.team9889.Linear;

import com.qualcomm.robotcore.util.*;
import com.team9889.Constants;
import com.team9889.Linear.auto.actions.Action;
import com.team9889.Linear.subsystems.*;

import camera_opmodes.LinearOpModeCamera;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpModeCamera {

    public Drive mDrive = Drive.getInstance();
    public Beacon mBeacon = Beacon.getInstance();
    public Intake mIntake = Intake.getInstance();
    public Flywheel mFlywheel = Flywheel.getInstance();

    private Team9889LinearOpMode mopMode = null;

    private ElapsedTime period = new ElapsedTime();

    protected void waitForTeamStart(Team9889LinearOpMode opMode){
        if (isCameraAvailable()){
            setCameraDownsampling(8);
            opMode.telemetry.addLine("Wait for camera to finish initializing!");
            opMode.telemetry.update();
            startCamera();  // can take a while.
            // best started before waitForStart
            opMode.telemetry.addLine("Camera ready!");
            opMode.telemetry.update();
        }

        telemetry.addData("Error", " Drive");
        telemetry.update();
        //Init Hardware
        mDrive.init(opMode.hardwareMap, true);

        telemetry.addData("Error", " Beacon");
        telemetry.update();
        mBeacon.init(opMode.hardwareMap, true);

        telemetry.addData("Error", " Intake");
        telemetry.update();
        mIntake.init(opMode.hardwareMap, true);

        telemetry.addData("Error", " Flywheel");
        telemetry.update();
        mFlywheel.init(opMode.hardwareMap, true);

        telemetry.addData("No Errors Init","");
        telemetry.update();

        //Zero Sensors
        mDrive.zeroSensors();
        mBeacon.zeroSensors();
        mFlywheel.zeroSensors();
        mIntake.zeroSensors();

        //Set init state of robot
        mDrive.DriveControlState(Drive.DriveControlState.POWER);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.FLOAT);

        mFlywheel.WantedState(Flywheel.WantedState.OFF);

        mIntake.WantedState(Intake.WantedState.WANTS_STOP);

        mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);

        mopMode = opMode;

        updateTelemetry();

        //Wait for DS start
        opMode.waitForStart();

        opMode.telemetry.addData("Started", Constants.OpMode);
        opMode.telemetry.update();
    }

    public void updateTelemetry(){
        mopMode.telemetry.addData("Running", Constants.OpMode);
        mDrive.outputToTelemetry(mopMode);
        mFlywheel.outputToTelemetry(mopMode);
        mIntake.outputToTelemetry(mopMode);
        mBeacon.outputToTelemetry(mopMode);
        outputToTelemetryForCamera(mopMode);
        mopMode.telemetry.update();
    }

    /**
     *
     * @param action All are defined in action folder
     */
    protected void runAction(Action action){
        boolean error = false;
        try {
            action.start(mopMode);
            updateTelemetry();
        } catch (Exception e){
            mopMode.telemetry.addData("Error in Starting Action", action);
            mopMode.telemetry.update();
            RobotLog.a("Error running Action " + action + " in start method in " + Constants.OpMode);
        }

        while (!action.isFinished() && mopMode.opModeIsActive() && !error){
            try {
                action.update(mopMode);
                updateTelemetry();
            } catch (Exception e){
                mopMode.telemetry.addData("Error in Updating Action", action);
                mopMode.telemetry.update();
                RobotLog.a("Error running Action " + action + " in update method in" + Constants.OpMode);
            }
        }

        if (mopMode.opModeIsActive() && !error){
            try {
                action.done();
                updateTelemetry();
            } catch (Exception e){
                mopMode.telemetry.addData("Error in Finishing Action", action);
                mopMode.telemetry.update();
                RobotLog.a("Error running Action " + action + " in done method in" + Constants.OpMode);
            }
        }

        mopMode.telemetry.addData("Finished Action", "");
        mopMode.telemetry.update();

        if(!error){
            mDrive.stop();
            mBeacon.stop();
            mFlywheel.stop();
            mIntake.stop();
            mopMode.sleep(5000);
        }
    }

    //Final Action to be run
    protected void finalAction(){
        try {
            mDrive.stop();
            mFlywheel.stop();
            mBeacon.stop();
            mIntake.stop();
        } catch (Exception e){
            RobotLog.a("Error Stop method" + Constants.OpMode);
        }

        stopCamera();

        mopMode.requestOpModeStop();
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
