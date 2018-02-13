package com.team9889.auto.actions.Drive;

import com.team9889.auto.actions.Action;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 8/4/2017.
 * Class to drive straight once at an angle
 *
 * See http://robotsforroboticists.com/pid-control/
 *
 */

public class DriveToDistance implements Action {
    // Drivetrain object
    private Drive mDrive = Robot.getInstance().getDrive();

    // Calculated left and right distances
    private int left, right = 0;

    // Wanted distance of the robot in inches
    private int mWantedDistance = 0;

    // Wanted angle
    private double mWantedAngle;

    // Default speed
    private double mSpeed = 5*Math.PI/2;

    // Proportional gain
    private double fkP = 14;

    // Integral gain
    private double fkI = 0;

    // Derivative gain
    private double fkD = 0.001;

    // Proportional gain
    private double bkP = 14;

    // Integral gain
    private double bkI = 0;

    // Derivative gain
    private double bkD = 0.001;

    private double error_prior = 0;

    private double integral=0;

    private long iteration_time = 2; // Milli

    // Is action finished?
    private boolean isFinished = false;

    public DriveToDistance(int Distance, double Angle){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
    }

    public DriveToDistance(int Distance, double Angle, double Speed) {
        mWantedDistance = Distance;
        mWantedAngle = Angle;
        mSpeed = Math.abs(Speed);
    }

    public DriveToDistance(int Distance, double Angle, double Speed, double P, double I, double D){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
        mSpeed = Math.abs(Speed);
        fkP = P;
        bkP = P;
        fkI = I;
        bkI = I;
        fkD = D;
        bkD = D;
    }

    @Override
    public void start() {
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        //  Calculate the wanted left and right distances
        left = mDrive.getLeftTicks() + inches2Ticks(mWantedDistance);
        right = mDrive.getRightTicks() + inches2Ticks(mWantedDistance);
    }

    @Override
    public void update() {
        // Calculate error
        double error = mDrive.getGyroAngleRadians() - CruiseLib.degreesToRadians(mWantedAngle);

        integral = integral + (error*iteration_time);
        //integral = CruiseLib.limitValue(integral, 0.004, -0.004);

        double derivative = (error - error_prior)/iteration_time;

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
                    mDrive.setVelocityTarget(mSpeed, mSpeed/1.5);
                } else if(mDrive.getGyroAngleDegrees()>0){
                    mDrive.setVelocityTarget(mSpeed/1.5, mSpeed);
                }
            } else {
                if(Math.abs(error)<1)
                    mDrive.SpeedTurn(mSpeed, (fkP*error)+(fkI*integral)+(fkD+derivative));
                else
                    mDrive.SpeedTurn(mSpeed, 0);

                error_prior = error;
                try {Thread.sleep(iteration_time);} catch (InterruptedException e) {}
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
                if(Math.abs(error)<1)
                    mDrive.SpeedTurn(-mSpeed, (bkP*error)+(bkI*integral)+(bkD+derivative));
                else
                    mDrive.SpeedTurn(-mSpeed, 0);
                error_prior = error;
                try {Thread.sleep(iteration_time);} catch (InterruptedException e) {}
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
