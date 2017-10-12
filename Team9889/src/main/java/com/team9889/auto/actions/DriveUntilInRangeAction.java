package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.CruiseLib;

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

    private MRDrive mMRDrive;

    public DriveUntilInRangeAction(double velocity, double minDistanceAway, double maxDistanceAway){
        mSpeed = velocity;
        mMinWantedDistance = minDistanceAway;
        mMaxWantedDistance = maxDistanceAway;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mMRDrive = opMode.mSuperstructure.getDrive();
        isFinished = false;
        LstartingDistance = mMRDrive.getLeftDistanceInches();
        RstartingDistance = mMRDrive.getRightDistanceInches();
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        isFinished = false;

        double CurrentLDistance = mMRDrive.getLeftDistanceInches() - LstartingDistance;
        double CurrentRDistance = mMRDrive.getRightDistanceInches() - RstartingDistance;

        if(CurrentRDistance > mMaxWantedDistance && CurrentLDistance > mMaxWantedDistance)
            mMRDrive.setLeftRightPower(-mSpeed, -mSpeed);
        else if (CurrentRDistance < mMinWantedDistance && CurrentLDistance < mMinWantedDistance)
            mMRDrive.setLeftRightPower(mSpeed, mSpeed);
        else if(CurrentRDistance < mMinWantedDistance && CurrentLDistance > mMaxWantedDistance)
            mMRDrive.setLeftRightPower(mSpeed, -mSpeed);
        else if(CurrentRDistance > mMaxWantedDistance && CurrentLDistance < mMinWantedDistance)
            mMRDrive.setLeftRightPower(-mSpeed, mSpeed);
        else if(CruiseLib.isBetween(CurrentRDistance, mMinWantedDistance, mMaxWantedDistance) && CruiseLib.isBetween(CurrentLDistance, mMinWantedDistance,mMaxWantedDistance)){
            mMRDrive.setLeftRightPower(0,0);
            isFinished = true;
        }
    }

    @Override
    public void done() {
        mMRDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }
}
