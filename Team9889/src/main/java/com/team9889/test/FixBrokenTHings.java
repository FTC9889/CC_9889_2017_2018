package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.TurnToAngle;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 12/30/2017.
 */

@Autonomous
public class FixBrokenTHings extends AutoModeBase {
    Drive mDrive;
    private double wantedAngle = 90;
    private double mSpeed = 0.6;
    private int timeOutTimerThing = 0;
    private int timeOut = 300;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

//        runAction(new DriveToDistance(30, 0, 0.6));

        Robot.getDrive().setLeftRightPower(0, 1);
        sleep(500);
        Robot.getDrive().setLeftRightPower(0,0);

        telemetry.addData("Right Pos", Robot.getDrive().getRightTicks());
        telemetry.update();
        sleep(1000);

        Robot.getDrive().setLeftRightPower(1,0);
        sleep(500);
        Robot.getDrive().setLeftRightPower(0,0);

        telemetry.clearAll();
        telemetry.addData("Left Pos", Robot.getDrive().getLeftTicks());
        telemetry.update();
        sleep(1000);
//
//        mDrive = Robot.getDrive();
//
//        mDrive.setLeftRightPower(Math.abs(mSpeed), -Math.abs(mSpeed));
//        boolean turning = true;
//        while (turning && opModeIsActive()){
//            updateTelemetry();
//            timeOutTimerThing++;
//            if (mDrive.getGyroAngleDegrees() > wantedAngle || timeOutTimerThing>timeOut)
//                turning = false;
//            Thread.yield();
//        }
//
//        mDrive.setLeftRightPower(-Math.abs(mSpeed)/1.5, Math.abs(mSpeed)/1.5);
//
//        turning = true;
//        timeOutTimerThing = 0;
//        while (turning && opModeIsActive()) {
//            updateTelemetry();
//            timeOutTimerThing++;
//            if (mDrive.getGyroAngleDegrees() < wantedAngle|| timeOutTimerThing>timeOut)
//                turning = false;
//            Thread.yield();
//        }


        //runAction(new TurnToAngle(90, 0.6));
        //runAction(new DriveToDistance(30, 0, 0.1));
        //sleep(1000);
        //runAction(new TurnToAngle(-90, 0.6));
        //runAction(new DriveStraightAction(-10, 0, 0.1));
    }
}
