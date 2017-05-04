package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.ftc2017.subsystems.Drive;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class DriveStraightAction implements Action {

    private double startingDistance;
    private double mWantedDistance;
    private double mVelocity;
    private double mAngle;
    private boolean DriveBackward;
    private boolean rv = false;
    private Drive mDrive = Drive.getInstance();

    public DriveStraightAction(double distance, double velocity){
        this(distance, velocity, 0);
    }

    public DriveStraightAction(double distance, double velocity, double angle){
        mWantedDistance = distance;
        mVelocity = velocity;
        mAngle = angle;
        DriveBackward = velocity < 0;
    }

    @Override
    public void start(HardwareMap hardwareMap) {
        mDrive.init(hardwareMap, false);
        startingDistance = getCurrentDistance();
        mAngle = mDrive.getGyroAngle();
        mDrive.DriveControlState(Drive.DriveControlState.SPEED);
        rv = false;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }

    @Override
    public boolean isFinished() {
        DriveBackward = mWantedDistance <0;

        if(DriveBackward) {
            if(mAngle > mDrive.getGyroAngle()){
                mDrive.setLeftRightPower(Math.abs(mVelocity)/2, Math.abs(mVelocity));
            }else if(mAngle < mDrive.getGyroAngle()){
                mDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity)/2);
            }else if(mAngle == mDrive.getGyroAngle()){
                mDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity));
            }
        }else {
            if(mAngle < mDrive.getGyroAngle()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity)/2, -Math.abs(mVelocity));
            }else if(mAngle > mDrive.getGyroAngle()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity)/2);
            }else if(mAngle == mDrive.getGyroAngle()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity));
            }
        }

        if (mWantedDistance > 0) {
            rv = getCurrentDistance() - startingDistance >= mWantedDistance;
        } else {
            rv = getCurrentDistance() - startingDistance <= mWantedDistance;
        }
        if (rv) {
            mDrive.setLeftRightPower(0, 0);
        }
        return rv;
    }

    @Override
    public void update(LinearOpMode linearOpMode){

    }


    private double getCurrentDistance() {
        return (mDrive.getLeftDistanceInches() + mDrive.getRightDistanceInches()) / 2;
    }
}
