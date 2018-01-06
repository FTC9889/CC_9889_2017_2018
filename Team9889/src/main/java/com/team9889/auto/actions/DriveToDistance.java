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

    // Drivetrain object
    private Drive mDrive;

    // Calculated left and right distances
    private int left, right = 0;

    // Wanted distance of the robot in inches
    private int mWantedDistance = 0;

    // Wanted angle
    private double mWantedAngle;

    // Default speed
    private double mSpeed = 5*Math.PI/2;

    // Proportional gain
    private double kP = 7;

    // Is action finished?
    private boolean isFinished = false;

    public DriveToDistance(int Distance, double Angle){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
    }

    public DriveToDistance(int Distance, double Angle, double Speed){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
        mSpeed = Math.abs(Speed);
    }

    @Override
    public void start(Team9889Linear opMode) {
        this.mDrive = opMode.Robot.getDrive();

        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        //  Calculate the wanted left and right distances
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

            // Strange case with the rev module.
            // bc the angle is wrapped to [-180.0, 180.0]
            if(mWantedAngle==180 || mWantedAngle==-180){
                if(Math.abs(mDrive.getGyroAngleDegrees())>179){
                    mDrive.setVelocityTarget(mSpeed, mSpeed);
                } else if(mDrive.getGyroAngleDegrees()<0){
                    mDrive.setVelocityTarget(mSpeed, mSpeed/2);
                } else if(mDrive.getGyroAngleDegrees()>0){
                    mDrive.setVelocityTarget(mSpeed/2, mSpeed);
                }
            } else {
                mDrive.SpeedTurn(mSpeed, CruiseLib.degreesToRadians(error)*kP);
            }
        } else {
            if(mDrive.getLeftTicks() < left)
                isFinished = true;

            if(mDrive.getRightTicks() < right)
                isFinished = true;

            if(mWantedAngle==180 || mWantedAngle==-180){
                if(Math.abs(mDrive.getGyroAngleDegrees())>179){
                    mDrive.setVelocityTarget(-mSpeed, -mSpeed);
                } else if(mDrive.getGyroAngleDegrees()<0){
                    mDrive.setVelocityTarget(-mSpeed/2, -mSpeed);
                } else if(mDrive.getGyroAngleDegrees()>0){
                    mDrive.setVelocityTarget(-mSpeed, -mSpeed/2);
                }
            } else {
                mDrive.SpeedTurn(-mSpeed, CruiseLib.degreesToRadians(error)*kP);
            }
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

        // Sleep a little in order to let the robot come to a full stop
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}
