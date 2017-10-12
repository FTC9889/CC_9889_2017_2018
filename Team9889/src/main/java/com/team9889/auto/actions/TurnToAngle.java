package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.DriveControlStates;
import com.team9889.subsystems.DriveZeroPowerStates;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private int currentAngle;
    private int wantedAngle;
    private int mError;

    private double mSpeed;

    private MRDrive mMRDrive;

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
            mMRDrive.setLeftRightPower(-Math.abs(mSpeed), Math.abs(mSpeed));
        }else if(mError > 0){
            mMRDrive.setLeftRightPower(Math.abs(mSpeed), -Math.abs(mSpeed));
        }else{
            mMRDrive.setLeftRightPower(0,0);
            finished = true;
        }

        return finished;
    }

    @Override
    public void done() {
        mMRDrive.setLeftRightPower(0,0);
        mMRDrive.DriveZeroPowerState(DriveZeroPowerStates.FLOAT);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        currentAngle = mMRDrive.getGyroAngleDegrees();
        mError = wantedAngle - currentAngle;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mMRDrive = opMode.mSuperstructure.getDrive();
        mMRDrive.setLeftRightPower(0,0);
        mMRDrive.DriveControlState(DriveControlStates.POWER);
        mMRDrive.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);
    }
}
