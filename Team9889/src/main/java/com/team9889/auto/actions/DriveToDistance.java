package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 8/4/2017.
 */

public class DriveToDistance implements Action {

    private Drive mDrive;

    private int left, right = 0;
    private int mWantedDistance = 0;

    private double mWantedAngle, mSpeed = 0;
    private double mLowerSpeed = 0;

    private boolean isFinished = false;

    public DriveToDistance(int Distance, double Angle){
        new DriveToDistance(Distance, Angle, 0.4);
    }

    public DriveToDistance(int Distance, double Angle, double Speed){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
        mSpeed = Speed;
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();
        left = mDrive.getLeftTicks() + inches2Ticks(mWantedDistance);
        right = mDrive.getRightTicks() + inches2Ticks(mWantedDistance);
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mLowerSpeed = mSpeed/3;
    }

    @Override
    public void update(Team9889Linear linearOpMode) {
        if(mWantedDistance > 0){
            if(left < mDrive.getLeftTicks())
                isFinished = true;

            if(right < mDrive.getRightTicks())
                isFinished = true;

            mSpeed = Math.abs(mSpeed);
            mLowerSpeed = Math.abs(mLowerSpeed);

            if (mWantedAngle < 0.0){
                if(mDrive.getGyroAngleDegrees() > mWantedAngle)
                    mDrive.setLeftRightPower(mSpeed, mLowerSpeed);
                else if(mDrive.getGyroAngleDegrees() < mWantedAngle)
                    mDrive.setLeftRightPower(mLowerSpeed, mSpeed);
                else
                    mDrive.setLeftRightPower(mSpeed, mSpeed);
            } else if (mWantedAngle > 0.0){
                if(mDrive.getGyroAngleDegrees() < mWantedAngle)
                    mDrive.setLeftRightPower(mSpeed, mLowerSpeed);
                else if(mDrive.getGyroAngleDegrees() > mWantedAngle)
                    mDrive.setLeftRightPower(mLowerSpeed, mSpeed);
                else
                    mDrive.setLeftRightPower(mSpeed, mSpeed);
            } else if(mWantedAngle == 0.0){ // mWantedAngle == 0
                if(mDrive.getGyroAngleDegrees()<0)
                    mDrive.setLeftRightPower(mSpeed, mLowerSpeed);
                else if (mDrive.getGyroAngleDegrees()>0)
                    mDrive.setLeftRightPower(mLowerSpeed, mSpeed);
                else
                    mDrive.setLeftRightPower(mSpeed, mSpeed);
            } else if(mWantedAngle == 180){ // why do you do this to me rev???

            }
        } else{
            if(left > mDrive.getLeftTicks())
                isFinished = true;

            if(right > mDrive.getRightTicks())
                isFinished = true;

            mSpeed = -Math.abs(mSpeed);
            mLowerSpeed = -Math.abs(mLowerSpeed);

            if (mWantedAngle < 0.0){
                if(mDrive.getGyroAngleDegrees() > mWantedAngle)
                    mDrive.setLeftRightPower(mLowerSpeed, mSpeed);
                else if(mDrive.getGyroAngleDegrees() < mWantedAngle)
                    mDrive.setLeftRightPower(mSpeed, mLowerSpeed);
                else
                    mDrive.setLeftRightPower(mSpeed, mSpeed);
            } else if (mWantedAngle > 0.0){
                if(mDrive.getGyroAngleDegrees() < mWantedAngle)
                    mDrive.setLeftRightPower(mLowerSpeed, mSpeed);
                else if(mDrive.getGyroAngleDegrees() > mWantedAngle)
                    mDrive.setLeftRightPower(mSpeed, mLowerSpeed);
                else
                    mDrive.setLeftRightPower(mSpeed, mSpeed);
            } else if(mWantedAngle == 0.0){ // mWantedAngle == 0
                if(mDrive.getGyroAngleDegrees()<0)
                    mDrive.setLeftRightPower(mLowerSpeed, mSpeed);
                else if (mDrive.getGyroAngleDegrees()>0)
                    mDrive.setLeftRightPower(mSpeed, mLowerSpeed);
                else
                    mDrive.setLeftRightPower(mSpeed, mSpeed);
            } else if(mWantedAngle == 180){ // why do you do this to me rev???

            }
        }

    }

    @Override
    public void done() {
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
        mDrive.setLeftRightPower(0,0);
    }
}
