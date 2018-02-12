package com.team9889.auto.actions.Drive;

import com.team9889.auto.actions.Action;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private double wantedAngle;
    private double error = 3;
    private double error_prior = 0;

    // Proportional gain
    private double kP = 9;

    // Integral gain
    private double kI = 0.0;

    // Derivative gain
    private double kD = 0;

    private double integral=0;
    private long iteration_time = 2; // Milli

    private double leftPow, rightPow;

    private Drive mDrive = Robot.getInstance().getDrive();

    /**
     *
     * @param angle Desired angle in degrees
     *              Positive angle is left
     *              Negative angle is right
     */
    public TurnToAngle(int angle){
        wantedAngle = angle;
    }

    @Override
    public void start() {
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }

    @Override
    public void update() {
        if(wantedAngle==180||wantedAngle==-180){
            if(mDrive.getGyroAngleDegrees()>0){
                mDrive.setVelocityTarget(-Math.PI/3, Math.PI/3);
            } else if(mDrive.getGyroAngleDegrees()<0){
                mDrive.setVelocityTarget(Math.PI/3, -Math.PI/3);
            } else {
                mDrive.setVelocityTarget(0, 0);
            }
        } else {
            error = mDrive.getGyroAngleRadians() - CruiseLib.degreesToRadians(wantedAngle);
            integral = integral + (error*iteration_time);
            double derivative = (error - error_prior)/iteration_time;

            leftPow = (error * kP) + (integral * kI) + (derivative * kD);
            rightPow = -((error * kP) + (integral * kI) + (derivative * kD));

            mDrive.setVelocityTarget(leftPow, rightPow);

            try {
                Thread.sleep(iteration_time);
            } catch (Exception e){}
        }
    }

    @Override
    public boolean isFinished() {
        if(wantedAngle==180||wantedAngle==-180){
            return Math.abs(mDrive.getGyroAngleDegrees())>178;
        } else {
            return Math.abs(error)<CruiseLib.degreesToRadians(2);
        }
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {}
    }

}
