package com.team9889.ftc2017;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.ftc2017.subsystems.Beacon;
import com.team9889.ftc2017.subsystems.Drive;
import com.team9889.ftc2017.subsystems.Flywheel;
import com.team9889.ftc2017.subsystems.Intake;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {
    public static Drive mDrive = Drive.getInstance();
    public static Flywheel mFlywheel = Flywheel.getInstance();
    public static Intake mIntake = Intake.getInstance();
    public static Beacon mBeacon = Beacon.getInstance();

    public ElapsedTime autonTimer = new ElapsedTime();

    private ElapsedTime period = new ElapsedTime();

    public void waitForTeamStart(HardwareMap hardwareMap, LinearOpMode opMode){
        mDrive.init(hardwareMap, true);
        mBeacon.init(hardwareMap, true);
        mIntake.init(hardwareMap, true);
        mFlywheel.init(hardwareMap, true);

        mDrive.DriveControlState(Drive.DriveControlState.POWER);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.FLOAT);

        mFlywheel.WantedState(Flywheel.WantedState.OFF);

        mIntake.WantedState(Intake.WantedState.WANTS_STOP);

        mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);

        updateTelemtry(opMode);

        opMode.waitForStart();
        autonTimer.reset();
    }

    public void updateTelemtry(LinearOpMode opMode){
        opMode.telemetry.clearAll();
        mDrive.outputToTelemtry(opMode);
        mFlywheel.outputToTelemtry(opMode);
        mIntake.outputToTelemtry(opMode);
        mBeacon.outputToTelemtry(opMode);
        opMode.telemetry.update();
    }

    public void finalAction(LinearOpMode linearOpMode){
        mDrive.stop();
        mFlywheel.stop();
        mBeacon.stop();
        mIntake.stop();

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
