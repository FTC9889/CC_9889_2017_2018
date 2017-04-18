package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.ftc2017.subsystems.Drive;

/**
 * Created by Joshua H on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private int initAngle;
    private int wantedAngle;
    private int mError;

    private double mSpeed;

    Drive mDrive = Drive.getInstance();

    public TurnToAngle(int angle, double speed){
        wantedAngle = angle;
        mSpeed = speed;
    }

    @Override
    public boolean isFinished() {
        boolean finished = false;
        if(mError < 0){
            mDrive.setLeftRightPower(-mSpeed, mSpeed);
        }else if(mError > 0){
            mDrive.setLeftRightPower(mSpeed, -mSpeed);
        }else{
            finished = true;
        }

        return finished;
    }

    @Override
    public void done() {
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.BRAKE);
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.FLOAT);
    }

    @Override
    public void update(LinearOpMode linearOpMode) {
        mError = wantedAngle - initAngle;



    }

    @Override
    public void start() {
        mDrive.DriveControlState(Drive.DriveControlState.POWER);
        initAngle = mDrive.geGyroHeading();
    }
}
