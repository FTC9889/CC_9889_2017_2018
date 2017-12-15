package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private double wantedAngle, mSpeed;
    private int timeOutTimerThing = 0;
    private int timeOut = 300;

    private Drive mDrive;

    /**
     *
     * @param angle Desired angle
     *              Positive angle is left
     *              Negative angle is right
     * @param speed Speed of turn
     */
    public TurnToAngle(int angle, double speed){
        wantedAngle = angle;
        mSpeed = speed;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        Thread.yield();
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);
    }

    @Override
    public void update(Team9889Linear linearOpMode) {}

    @Override
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();
        mDrive.DriveControlState(Drive.DriveControlStates.POWER);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        if(wantedAngle>0){
            mDrive.setLeftRightPower(-Math.abs(mSpeed), Math.abs(mSpeed));
            boolean turning = true;
            while (turning && opMode.opModeIsActive()){
                opMode.updateTelemetry();
                timeOutTimerThing++;
                if (mDrive.getGyroAngleDegrees() > wantedAngle || timeOutTimerThing>timeOut)
                    turning = false;
                Thread.yield();
            }

            mDrive.setLeftRightPower(Math.abs(mSpeed)/1.3, -Math.abs(mSpeed)/1.3);

            turning = true;
            timeOutTimerThing = 0;
            while (turning && opMode.opModeIsActive()) {
                opMode.updateTelemetry();
                timeOutTimerThing++;
                if (mDrive.getGyroAngleDegrees() < wantedAngle|| timeOutTimerThing>timeOut)
                    turning = false;
                Thread.yield();
            }
        } else {
            mDrive.setLeftRightPower(Math.abs(mSpeed), -Math.abs(mSpeed));
            boolean turning = true;
            while (turning && opMode.opModeIsActive()){
                opMode.updateTelemetry();
                timeOutTimerThing++;
                if (mDrive.getGyroAngleDegrees() < wantedAngle|| timeOutTimerThing>timeOut)
                    turning = false;
                Thread.yield();
            }

            mDrive.setLeftRightPower(-Math.abs(mSpeed)/1.3, Math.abs(mSpeed)/1.3);

            turning = true;
            while (turning && opMode.opModeIsActive()) {
                opMode.updateTelemetry();
                timeOutTimerThing++;
                if (mDrive.getGyroAngleDegrees() > wantedAngle|| timeOutTimerThing>timeOut)
                    turning = false;
                Thread.yield();
            }
        }

        mDrive.setLeftRightPower(0,0);
    }
}
