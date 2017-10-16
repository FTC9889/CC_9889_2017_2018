package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.DriveControlStates;
import com.team9889.subsystems.DriveZeroPowerStates;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private double currentAngle;
    private double wantedAngle;
    private double mError;

    private double mSpeed;

    private Drive mDrive;

    /**
     *
     * @param angle Desired angle
     *              Positive angle is right
     *              Negative angle is left
     * @param speed Speed of turn
     */
    public TurnToAngle(int angle, double speed){
        wantedAngle = angle;
        mSpeed = speed;
    }

    @Override
    public boolean isFinished() {
        boolean finished = false;
        if(mError < 0){
            mDrive.setLeftRightPower(-Math.abs(mSpeed), Math.abs(mSpeed));
        }else if(mError > 0){
            mDrive.setLeftRightPower(Math.abs(mSpeed), -Math.abs(mSpeed));
        }else{
            mDrive.setLeftRightPower(0,0);
            finished = true;
        }

        return finished;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveZeroPowerState(DriveZeroPowerStates.FLOAT);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        currentAngle = mDrive.getGyroAngleDegrees();
        mError = wantedAngle - currentAngle;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mDrive = opMode.Robot.getDrive();
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveControlState(DriveControlStates.POWER);
        mDrive.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);
    }
}
