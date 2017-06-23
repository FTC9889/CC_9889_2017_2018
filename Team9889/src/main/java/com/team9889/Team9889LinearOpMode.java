package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.*;
import com.team9889.auto.actions.Action;
import com.team9889.lib.Camera_Flash;
import com.team9889.subsystems.*;

import camera_opmodes.LinearOpModeCamera;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpModeCamera {

    public Drive mDrive = Drive.getInstance();
    public Beacon mBeacon = Beacon.getInstance();
    public Intake mIntake = Intake.getInstance();
    public Flywheel mFlywheel = Flywheel.getInstance();
    public Camera_Flash camera_flash = new Camera_Flash();

    private ElapsedTime period = new ElapsedTime();

    protected void waitForTeamStart(LinearOpMode opMode){
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

        updateTelemetry(opMode);

        //Wait for DS start
        opMode.waitForStart();

        opMode.telemetry.addData("Started", Constants.OpMode);
        opMode.telemetry.update();
    }

    public void updateTelemetry(LinearOpMode opMode){
        opMode.telemetry.addData("Running", Constants.OpMode);
        mDrive.outputToTelemetry(opMode);
        mFlywheel.outputToTelemetry(opMode);
        mIntake.outputToTelemetry(opMode);
        mBeacon.outputToTelemetry(opMode);
        outputToTelemetryForCamera(opMode);
        opMode.telemetry.update();
    }

    /**
     *
     * @param action All are defined in action folder
     * @param opMode just type in "this"
     */
    protected void runAction(Action action, Team9889LinearOpMode  opMode){
        boolean error = false;
        try {
            action.start(opMode);
            updateTelemetry(opMode);
        } catch (Exception e){
            opMode.telemetry.addData("Error in Starting Action", action);
            opMode.telemetry.update();
            RobotLog.a("Error running Action " + action + " in start method in " + Constants.OpMode);
        }

        while (!action.isFinished() && opMode.opModeIsActive() && !error){
            try {
                action.update(opMode);
                updateTelemetry(opMode);
            } catch (Exception e){
                opMode.telemetry.addData("Error in Updating Action", action);
                opMode.telemetry.update();
                RobotLog.a("Error running Action " + action + " in update method in" + Constants.OpMode);
            }
        }

        if (opMode.opModeIsActive() && !error){
            try {
                action.done();
                updateTelemetry(opMode);
            } catch (Exception e){
                opMode.telemetry.addData("Error in Finishing Action", action);
                opMode.telemetry.update();
                RobotLog.a("Error running Action " + action + " in done method in" + Constants.OpMode);
            }
        }

        opMode.telemetry.addData("Finished Action", "");
        opMode.telemetry.update();

        if(!error){
            mDrive.stop();
            mBeacon.stop();
            mFlywheel.stop();
            mIntake.stop();
            opMode.sleep(5000);
        }
    }

    //Final Action to be run
    protected void finalAction(Team9889LinearOpMode  opMode){
        try {
            mDrive.stop();
            mFlywheel.stop();
            mBeacon.stop();
            mIntake.stop();
        } catch (Exception e){
            RobotLog.a("Error Stop method" + Constants.OpMode);
        }

        stopCamera();

        opMode.requestOpModeStop();
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
