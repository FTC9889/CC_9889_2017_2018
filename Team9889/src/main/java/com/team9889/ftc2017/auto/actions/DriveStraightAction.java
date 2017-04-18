package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.ftc2017.subsystems.Drive;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class DriveStraightAction implements Action {

    private double startingDistance;
    private double mWantedDistance;
    private double mVelocity;
    private double mHeading;
    private Drive mDrive = Drive.getInstance();

    public DriveStraightAction(double distance, double velocity){
        this(distance, velocity, 0);
    }

    public DriveStraightAction(double distance, double velocity, double heading){
        mWantedDistance = distance;
        mVelocity = velocity;
        mHeading = heading;
    }

    @Override
    public void start() {
        startingDistance = getCurrentDistance();
        mHeading = mDrive.geGyroHeading();
        mDrive.DriveControlState(Drive.DriveControlState.SPEED);
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        boolean rv = false;
        if (mWantedDistance > 0) {
            rv = getCurrentDistance() - startingDistance >= mWantedDistance;
        } else {
            rv = getCurrentDistance() - startingDistance <= mWantedDistance;
        }
        if (rv) {
            mDrive.setLeftRightPower(0, 0);
        }
        return rv;
    }

    @Override
    public void update(LinearOpMode linearOpMode){

    }


    private double getCurrentDistance() {
        return (mDrive.getLeftDistanceInches() + mDrive.getRightDistanceInches()) / 2;
    }
}
