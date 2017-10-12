package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 5/5/17.
 */

public class DriveTimeAction implements Action {
    private double Milliseconds;
    private double StartMilliseconds;
    private double mVelocity;
    private double mAngle;
    private boolean isFinished;

    private MRDrive mMRDrive;

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
        mMRDrive = opMode.mSuperstructure.getDrive();
        mAngle = mMRDrive.getGyroAngleDegrees();
        StartMilliseconds = opMode.getRuntime();
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        boolean DriveBackward;

        DriveBackward = mVelocity <0;

        if(DriveBackward) {
            if(mAngle > mMRDrive.getGyroAngleDegrees()){
                mMRDrive.setLeftRightPower(Math.abs(mVelocity)/2, Math.abs(mVelocity));
            }else if(mAngle < mMRDrive.getGyroAngleDegrees()){
                mMRDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity)/2);
            }else if(mAngle == mMRDrive.getGyroAngleDegrees()){
                mMRDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity));
            }
        }else {
            if(mAngle < mMRDrive.getGyroAngleDegrees()){
                mMRDrive.setLeftRightPower(-Math.abs(mVelocity)/2, -Math.abs(mVelocity));
            }else if(mAngle > mMRDrive.getGyroAngleDegrees()){
                mMRDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity)/2);
            }else if(mAngle == mMRDrive.getGyroAngleDegrees()){
                mMRDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity));
            }
        }

        double CurrentMilliseconds = linearOpMode.getRuntime() - StartMilliseconds;
        isFinished = CurrentMilliseconds>Milliseconds;
    }

    @Override
    public void done() {
        mMRDrive.setLeftRightPower(0,0);
    }
}
