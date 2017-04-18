package com.team9889.ftc2017.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.ftc2017.auto.actions.Action;
import com.team9889.ftc2017.subsystems.Beacon;
import com.team9889.ftc2017.subsystems.Drive;
import com.team9889.ftc2017.subsystems.Flywheel;
import com.team9889.ftc2017.subsystems.Intake;

/**
 * Created by Joshua H on 4/10/2017.
 *
 * An abstract class that is the basis of the robot's autonomous routines. This
 * is implemented in auto.modes (which are routines that do actions).
 */
public abstract class AutoModeBase extends LinearOpMode{
    public static Drive mDrive = Drive.getInstance();
    public static Flywheel mFlywheel = Flywheel.getInstance();
    public static Intake mIntake = Intake.getInstance();
    public static Beacon mBeacon = Beacon.getInstance();

    public ElapsedTime autonTimer = new ElapsedTime();

    public void waitForBaseStart(HardwareMap hardwareMap, LinearOpMode opMode){
        mDrive.init(hardwareMap, true);
        mBeacon.init(hardwareMap, true);
        mIntake.init(hardwareMap, true);
        mFlywheel.init(hardwareMap, true);

        mDrive.DriveControlState(Drive.DriveControlState.SPEED);
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

    public void runAction(Action action, LinearOpMode opMode){
        action.start();
        while (!action.isFinished() && opMode.opModeIsActive()){
            action.update(opMode);
        }
        action.done();
    }

    public void finalAction(LinearOpMode linearOpMode){
        mDrive.stop();
        mFlywheel.stop();
        mBeacon.stop();
        mIntake.stop();

        linearOpMode.requestOpModeStop();
    }
}
