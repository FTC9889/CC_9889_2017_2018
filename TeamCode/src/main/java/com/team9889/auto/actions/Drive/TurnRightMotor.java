package com.team9889.auto.actions.Drive;

import com.team9889.auto.actions.Action;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class TurnRightMotor implements Action {
    private Drive mDrive = Robot.getInstance().getDrive();

    private double wantedAngle;
    double error = 3;
    double Kp = 7.2;
    private double rightPow;

    public TurnRightMotor(double angle){
        this.wantedAngle = angle;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(error)<2;
    }

    @Override
    public void start() {
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }

    @Override
    public void update() {
        if(wantedAngle==180){
            wantedAngle=179;
        }else if(wantedAngle==-180){
            wantedAngle=-179;
        } else {
            error = mDrive.getGyroAngleDegrees() - wantedAngle;
            rightPow = -CruiseLib.degreesToRadians(error) * Kp;
            mDrive.setVelocityTarget(0.0, rightPow);
        }
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}
