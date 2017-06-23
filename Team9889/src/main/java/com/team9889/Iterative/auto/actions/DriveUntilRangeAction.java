package com.team9889.Iterative.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.team9889.Iterative.subsystems.Drive;

/**
 * Created by joshua on 6/21/17.
 */

public class DriveUntilRangeAction implements Action {
    private double mMinDistanceAway, mMaxDistanceToDrive, mVelocity;
    private double startingDistance;
    private Drive mDrive = Drive.getInstance();

    public DriveUntilRangeAction(double Velocity, double MinDistanceAway, double MacDistanceToDrive){
        mMinDistanceAway = MinDistanceAway;
        mMaxDistanceToDrive = MacDistanceToDrive;
        mVelocity = Velocity;
    }

    @Override
    public boolean isFinished() {
        if (getCurrentDistance() - startingDistance >= mMaxDistanceToDrive) {
            return true;
        }

        return false;
    }

    @Override
    public void start(OpMode opMode) {
        mDrive.init(opMode, true);
        mDrive.DriveControlState(Drive.DriveControlState.POWER);
        mDrive.setLeftRightPower(mVelocity, mVelocity);
    }

    @Override
    public void update(OpMode opMode) {
        if(mDrive.getGyroAngleDegrees() < 0)
            mDrive.setLeftRightPower(mVelocity/2, mVelocity);
        else if (mDrive.getGyroAngleDegrees() > 0)
            mDrive.setLeftRightPower(mVelocity, mVelocity/2);
    }

    @Override
    public void done() {

    }

    private double getCurrentDistance() {
        return (mDrive.getLeftDistanceInches() + mDrive.getRightDistanceInches()) / 2;
    }
}
