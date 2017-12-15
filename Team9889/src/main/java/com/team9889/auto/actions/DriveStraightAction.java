package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;


/**
 * Created by joshua9889 on 4/10/2017.
 */

public class DriveStraightAction implements Action {

    private double mWantedDistance, mVelocity, mAngle;
    private double leftVelocity, rightVelocity;
    private boolean DriveBackward, rv = false;
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
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void update(Team9889Linear opMode){
        mDrive.setLeftRightPower(mVelocity, mVelocity);
    }
}
