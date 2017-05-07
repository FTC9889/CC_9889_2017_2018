package com.team9889.ftc2017;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.*;
import com.team9889.ftc2017.auto.actions.Action;
import com.team9889.ftc2017.subsystems.*;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {

    public Drive mDrive = Drive.getInstance();
    public Beacon mBeacon = Beacon.getInstance();
    public Intake mIntake = Intake.getInstance();
    public Flywheel mFlywheel = Flywheel.getInstance();

    private ElapsedTime period = new ElapsedTime();

    private ElapsedTime runtime = new ElapsedTime();

    private String particlePref;
    private String beaconPref;
    private String capBallPref;
    private String parkingPref;
    private String alliance;

    protected void waitForTeamStart(LinearOpMode opMode){
        telemetry.addData("Error", "Drive");
        telemetry.update();
        //Init Hardware
        mDrive.init(opMode.hardwareMap, true);

        telemetry.addData("Error", "Beacon");
        telemetry.update();
        mBeacon.init(opMode.hardwareMap, true);


        telemetry.addData("Error", "Intake");
        telemetry.update();
        mIntake.init(opMode.hardwareMap, true);

        telemetry.addData("Error", "Flywheel");
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

        if(Constants.OpMode != "TELEOP" && Constants.OpMode != "Teleop" && Constants.OpMode != "teleop"){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);
            particlePref = preferences.getString("How Many Particles Should We Shoot?", "");
            beaconPref = preferences.getString("Which beacons should we activate?", "");
            capBallPref = preferences.getString("Should we bump the cap ball off the center vortex?", "");
            parkingPref = preferences.getString("Where should we park?", "");
            alliance = preferences.getString("Which alliance are we on?", "");

            telemetry.addLine("Particles: " + particlePref);
            telemetry.addLine("Beacons: " + beaconPref);
            telemetry.addLine("Cap Ball: " + capBallPref);
            telemetry.addLine("Parking: " + parkingPref);
            telemetry.addLine("Alliance: " + alliance);
        }

        updateTelemtry(opMode);

        //Wait for DS start
        opMode.waitForStart();

        opMode.telemetry.addData("Started", Constants.OpMode);
        opMode.telemetry.update();
    }

    public void updateTelemtry(LinearOpMode opMode){
        opMode.telemetry.addData("Running", Constants.OpMode);
        mDrive.outputToTelemtry(opMode);
        mFlywheel.outputToTelemtry(opMode);
        mIntake.outputToTelemtry(opMode);
        mBeacon.outputToTelemtry(opMode);
        opMode.telemetry.addData("Runtime", runtime.milliseconds());
        opMode.telemetry.update();
    }

    /**
     *
     * @param action
     * @param opMode just type in "this"
     */
    protected void runAction(Action action, LinearOpMode opMode){
        boolean error = false;
        try {
            action.start(opMode.hardwareMap);
            updateTelemtry(opMode);
        } catch (Exception e){
            opMode.telemetry.addData("Error in Starting Action", action);
            opMode.telemetry.update();
            RobotLog.a("Error running Action " + action + " in start method in " + Constants.OpMode);
            error = true;
        }

        while (!action.isFinished() && opMode.opModeIsActive() && !error){
            try {
                action.update(opMode);
                updateTelemtry(opMode);
            } catch (Exception e){
                opMode.telemetry.addData("Error in Updating Action", action);
                opMode.telemetry.update();
                RobotLog.a("Error running Action " + action + " in update method in" + Constants.OpMode);
                error = true;
            }
        }

        try {
            action.done();
            updateTelemtry(opMode);
        } catch (Exception e){
            opMode.telemetry.addData("Error in Finishing Action", action);
            opMode.telemetry.update();
            RobotLog.a("Error running Action " + action + " in done method in" + Constants.OpMode);
            error = true;
        }

        if(!error){
            opMode.requestOpModeStop();
        }
    }

    //Final Action to be run
    protected void finalAction(LinearOpMode linearOpMode){
        try {
            mDrive.stop();
            mFlywheel.stop();
            mBeacon.stop();
            mIntake.stop();
        } catch (Exception e){
            RobotLog.a("Error Stop method" + Constants.OpMode);
        }

        linearOpMode.requestOpModeStop();
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
