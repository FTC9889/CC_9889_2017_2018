package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.ftc2017.subsystems.Drive;

/**
 * Created by Joshua H on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private int currentAngle;
    private int wantedAngle;
    private int mError;

    private double mSpeed;

    private Drive mDrive = Drive.getInstance();

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
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.BRAKE);
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.FLOAT);
    }

    @Override
    public void update(LinearOpMode linearOpMode) {
        currentAngle = mDrive.getGyroAngle();
        mError = wantedAngle - currentAngle;
    }

    @Override
    public void start(HardwareMap hardwareMap) {
        mDrive.init(hardwareMap, false);
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveControlState(Drive.DriveControlState.POWER);
    }
}
