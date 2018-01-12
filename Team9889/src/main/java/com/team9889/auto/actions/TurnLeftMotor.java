package com.team9889.auto.actions;

import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/24/2017.
 * Class to turn with a single motor (left)
 */

public class TurnLeftMotor implements Action {
    private Drive mDrive = Robot.getInstance().getDrive();

    private double wantedAngle;
    private double error = 3;
    private double Kp = 7.2;

    public TurnLeftMotor(double angle){
        this.wantedAngle = angle;
    }

    @Override
    public void start() {
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }

    @Override
    public void update() {
        // We cannot turn to exactly 180 because the Rev IMU valid values are [-180,180]
        if(wantedAngle==180||wantedAngle==-180){
            wantedAngle= 179*(wantedAngle/180);
        } else {
            error = mDrive.getGyroAngleDegrees() - wantedAngle;

            double leftPow = CruiseLib.degreesToRadians(error) * Kp;
            mDrive.setVelocityTarget(leftPow, 0.0);
        }
    }

    @Override
    public boolean isFinished() {
        return Math.abs(error)<2;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {}
    }
}
