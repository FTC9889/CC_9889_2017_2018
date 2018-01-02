package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 8/4/2017.
 * Class to drive straight once at an angle
 */

public class DriveToDistance implements Action {

    private Drive mDrive;

    private int left, right = 0;
    private int mWantedDistance = 0;

    private double mWantedAngle;
    private double mSpeed = 5*Math.PI/2;
    double kP = 7;

    private boolean isFinished = false;

    public DriveToDistance(int Distance, double Angle){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
    }

    public DriveToDistance(int Distance, double Angle, double Speed){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
        mSpeed = Speed;
    }

    @Override
    public void start(Team9889Linear opMode) {
        this.mDrive = opMode.Robot.getDrive();

        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        left = mDrive.getLeftTicks() + inches2Ticks(mWantedDistance);
        right = mDrive.getRightTicks() + inches2Ticks(mWantedDistance);
    }

    @Override
    public void update(Team9889Linear linearOpMode) {
        // Calculate error
        double error = mDrive.getGyroAngleDegrees() - mWantedAngle;

        // Are we going foward or backward
        if(mWantedDistance > 0){
            if(mDrive.getLeftTicks() > left)
                isFinished = true;

            if(mDrive.getRightTicks() > right)
                isFinished = true;

            mDrive.SpeedTurn(mSpeed, CruiseLib.degreesToRadians(error)*kP);

        } else {
            if(mDrive.getLeftTicks() < left)
                isFinished = true;

            if(mDrive.getRightTicks() < right)
                isFinished = true;

            mDrive.SpeedTurn(-mSpeed, CruiseLib.degreesToRadians(error)*kP);
        }
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void done() {
        mDrive.DriveControlState(Drive.DriveControlStates.POWER);
        mDrive.setLeftRightPower(0,0);

        // Sleep a little in order to let the robot
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}
