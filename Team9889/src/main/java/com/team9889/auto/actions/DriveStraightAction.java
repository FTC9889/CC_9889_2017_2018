package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.CruiseLib;


/**
 * Created by joshua9889 on 4/10/2017.
 */

public class DriveStraightAction implements Action {

    private double mWantedDistance, mVelocity, mAngle;
    private double leftVelocity, rightVelocity;
    private boolean DriveBackward, rv = false;
    private MRDrive mMRDrive;

    public DriveStraightAction(double distance, double velocity){
        this(distance, velocity, 0);
    }

    public DriveStraightAction(double distance, double velocity, double angle){
        mWantedDistance = distance;
        mVelocity = velocity;
        mAngle = angle;
        DriveBackward = velocity < 0;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mMRDrive = opMode.mSuperstructure.getDrive();
        mWantedDistance = CruiseLib.Average(mMRDrive.getLeftDistanceInches(), mMRDrive.getRightDistanceInches()) + mWantedDistance;
    }

    @Override
    public void done() {
        mMRDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        return !((Math.abs(mMRDrive.getRightDistanceInches())) > Math.abs(mWantedDistance));
    }

    @Override
    public void update(Team9889LinearOpMode  opMode){
        mMRDrive.setLeftRightPower(mVelocity, mVelocity);
    }
}
