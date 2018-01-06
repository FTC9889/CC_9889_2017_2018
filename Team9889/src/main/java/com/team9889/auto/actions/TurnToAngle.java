package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private double wantedAngle;
    private double error = 3;
    private double Kp = 10;
    private double leftPow, rightPow;

    private Drive mDrive;

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
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();

        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }

    @Override
    public void update(Team9889Linear linearOpMode) {
        if(wantedAngle==180||wantedAngle==-180){
            wantedAngle= 179*(wantedAngle/180);
        } else {
            error = mDrive.getGyroAngleDegrees() - wantedAngle;
            leftPow = CruiseLib.degreesToRadians(error) * Kp;
            rightPow = -CruiseLib.degreesToRadians(error) * Kp;

            mDrive.setVelocityTarget(leftPow, rightPow);
        }
    }

    @Override
    public boolean isFinished() {
        return Math.abs(error)<2.5;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {}
    }

}
