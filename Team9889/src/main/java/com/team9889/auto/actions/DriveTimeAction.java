package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 5/5/17.
 */

public class DriveTimeAction implements Action {
    private double mSpeed;
    private int Milliseconds;
    private double mWantedAngle = 100000;
    private double kP = 7;

    private Drive mDrive;
    private ElapsedTime t = new ElapsedTime();

    public DriveTimeAction(int milliseconds, double speed) {
        Milliseconds = milliseconds;
        mSpeed = speed;
    }

    public DriveTimeAction(int milliseconds, double speed, double Angle){
        Milliseconds = milliseconds;
        mSpeed = speed;
        mWantedAngle = Angle;
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>Milliseconds;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
        t.reset();
    }

    @Override
    public void update(Team9889Linear opMode) {
        if(mWantedAngle<10000){
            // Calculate error
            double error = mDrive.getGyroAngleDegrees() - mWantedAngle;

            mDrive.SpeedTurn(mSpeed, CruiseLib.degreesToRadians(error)*kP);
        } else {
            mDrive.setLeftRightPower(mSpeed, mSpeed);
        }
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }
}
