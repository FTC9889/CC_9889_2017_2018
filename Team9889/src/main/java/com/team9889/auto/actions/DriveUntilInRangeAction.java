package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 4/16/2017.
 */

public class DriveUntilInRangeAction implements Action {
    private double LstartingDistance;
    private double RstartingDistance;
    private double mMinWantedDistance;
    private double mMaxWantedDistance;

    private boolean isFinished;

    private double mSpeed;

    private Drive mDrive;

    public DriveUntilInRangeAction(double velocity, double minDistanceAway, double maxDistanceAway){
        mSpeed = velocity;
        mMinWantedDistance = minDistanceAway;
        mMaxWantedDistance = maxDistanceAway;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();
        isFinished = false;
        LstartingDistance = mDrive.getLeftDistanceInches();
        RstartingDistance = mDrive.getRightDistanceInches();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {
        isFinished = false;

        double CurrentLDistance = mDrive.getLeftDistanceInches() - LstartingDistance;
        double CurrentRDistance = mDrive.getRightDistanceInches() - RstartingDistance;

        if(CurrentRDistance > mMaxWantedDistance && CurrentLDistance > mMaxWantedDistance)
            mDrive.setLeftRightPower(-mSpeed, -mSpeed);
        else if (CurrentRDistance < mMinWantedDistance && CurrentLDistance < mMinWantedDistance)
            mDrive.setLeftRightPower(mSpeed, mSpeed);
        else if(CurrentRDistance < mMinWantedDistance && CurrentLDistance > mMaxWantedDistance)
            mDrive.setLeftRightPower(mSpeed, -mSpeed);
        else if(CurrentRDistance > mMaxWantedDistance && CurrentLDistance < mMinWantedDistance)
            mDrive.setLeftRightPower(-mSpeed, mSpeed);
        else if(CruiseLib.isBetween(CurrentRDistance, mMinWantedDistance, mMaxWantedDistance) && CruiseLib.isBetween(CurrentLDistance, mMinWantedDistance,mMaxWantedDistance)){
            mDrive.setLeftRightPower(0,0);
            isFinished = true;
        }
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
