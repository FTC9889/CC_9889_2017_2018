package com.team9889.Linear.auto.actions;

import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.Linear.subsystems.Drive;

/**
 * Created by joshua on 5/5/17.
 */

public class DriveTimeAction implements Action {
    private double Milliseconds;
    private double StartMilliseconds;
    private double mVelocity;
    private double mAngle;
    private boolean isFinished;

    private Drive mDrive = Drive.getInstance();

    public DriveTimeAction(int milliseconds, double Velocity) {
        Milliseconds = milliseconds;
        mVelocity = Velocity;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void start(Team9889LinearOpMode  opMode) {
        mDrive = opMode.mDrive;
        mAngle = mDrive.getGyroAngleDegrees();
        StartMilliseconds = opMode.getRuntime();
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        boolean DriveBackward;

        DriveBackward = mVelocity <0;

        if(DriveBackward) {
            if(mAngle > mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(Math.abs(mVelocity)/2, Math.abs(mVelocity));
            }else if(mAngle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity)/2);
            }else if(mAngle == mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity));
            }
        }else {
            if(mAngle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity)/2, -Math.abs(mVelocity));
            }else if(mAngle > mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity)/2);
            }else if(mAngle == mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity));
            }
        }

        double CurrentMilliseconds = linearOpMode.getRuntime() - StartMilliseconds;
        isFinished = CurrentMilliseconds>Milliseconds;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }
}
