package com.team9889.ftc2017;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.ftc2017.auto.actions.Action;
import com.team9889.ftc2017.subsystems.Beacon;
import com.team9889.ftc2017.subsystems.Drive;
import com.team9889.ftc2017.subsystems.Flywheel;
import com.team9889.ftc2017.subsystems.Intake;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {

    public Drive mDrive = Drive.getInstance();
    public Beacon mBeacon = Beacon.getInstance();
    public Intake mIntake = Intake.getInstance();
    public Flywheel mFlywheel = Flywheel.getInstance();

    public HardwareMap mHardwareMap;

    private ElapsedTime period = new ElapsedTime();

    private ElapsedTime runtime = new ElapsedTime();

    public void waitForTeamStart(HardwareMap hardwareMap, LinearOpMode opMode){
        Constants.Runtime.reset();
        mHardwareMap = hardwareMap;

        //Init Hardware
        mDrive.init(hardwareMap, true);
        mBeacon.init(hardwareMap, true);
        mIntake.init(hardwareMap, true);
        mFlywheel.init(hardwareMap, true);

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

        updateTelemtry(opMode);

        //Wait for DS start
        opMode.waitForStart();
    }

    public void updateTelemtry(LinearOpMode opMode){
        opMode.telemetry.clearAll();
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
    public void runAction(Action action, LinearOpMode opMode){
        boolean error = false;
        try {
            action.start(mHardwareMap);
        } catch (Exception e){
            RobotLog.a("Error running Action " + action + " in start method in " + Constants.OpMode + " at " + Constants.Runtime + " after init.");
            error = true;
        }

        while (!action.isFinished() && opMode.opModeIsActive() && !error){
            try {
                action.update(opMode);
            } catch (Exception e){
                RobotLog.a("Error running Action " + action + " in update method in" + Constants.OpMode + " at " + Constants.Runtime + " after init.");
                error = true;
            }
        }

        try {
            action.done();
        } catch (Exception e){
            RobotLog.a("Error running Action " + action + " in done method in" + Constants.OpMode + " at " + Constants.Runtime + " after init.");
            error = true;
        }

        if(!error){
            opMode.requestOpModeStop();
        }
    }

    //Final Action to be run
    public void finalAction(LinearOpMode linearOpMode){
        try {
            mDrive.stop();
            mFlywheel.stop();
            mBeacon.stop();
            mIntake.stop();
        } catch (Exception e){
            RobotLog.a("Error Stop method" + Constants.OpMode + " at " + Constants.Runtime + " after init.");
        }

        linearOpMode.requestOpModeStop();
    }

    //Built-in function by FIRST. Put in all loops
    public void waitForTick(long periodMs) {
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
