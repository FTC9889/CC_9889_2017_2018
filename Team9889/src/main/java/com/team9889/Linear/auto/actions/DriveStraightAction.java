package com.team9889.Linear.auto.actions;

import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.lib.CruiseLib;
import com.team9889.Linear.subsystems.Drive;


/**
 * Created by Joshua H on 4/10/2017.
 */

public class DriveStraightAction implements Action {

    private double mWantedDistance;
    private double mVelocity;
    private double mAngle;
    private boolean DriveBackward;
    private boolean rv = false;
    private Drive mDrive;

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
        mDrive = opMode.mDrive;
        mWantedDistance = CruiseLib.Average(mDrive.getLeftDistanceInches(), mDrive.getRightDistanceInches()) + mWantedDistance;

    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        return !mDrive.InchesAreWeThereYet(mWantedDistance);
    }

    @Override
    public void update(Team9889LinearOpMode  opMode){

        mDrive.setLeftRightPower(mVelocity, mVelocity);
    }
}
