package com.team9889.Linear.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.Linear.subsystems.Drive;

/**
 * Created by joshua on 5/5/17.
 */

public class DriveTimeAction implements Action {
    private long Milliseconds;
    private double TargetTime;
    private double mVelocity;
    private double mAngle;

    private Drive mDrive = Drive.getInstance();

    public DriveTimeAction(int milliseconds, double Velocity) {
        Milliseconds = milliseconds;
        mVelocity = Velocity;
    }

    @Override
    public boolean isFinished() {
        boolean DriveBackward;

        DriveBackward = mVelocity <0;

        if(DriveBackward) {
            if(mAngle > mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(Math.abs(mVelocity)/2, Math.abs(mVelocity));
            }else if(mAngle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity)/2);
            }else if(mAngle == mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(Math.abs(mVelocity), Math.abs(mVelocity));
            }
        }else {
            if(mAngle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity)/2, -Math.abs(mVelocity));
            }else if(mAngle > mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity)/2);
            }else if(mAngle == mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(-Math.abs(mVelocity), -Math.abs(mVelocity));
            }
        }
        return TargetTime < System.currentTimeMillis();
    }

    @Override
    public void start(HardwareMap hardwareMap) {
        long StartTime;
        mDrive.init(hardwareMap, false);
        mAngle = mDrive.getGyroAngleDegrees();
        StartTime = System.currentTimeMillis();
        TargetTime = StartTime + Milliseconds;
    }

    @Override
    public void update(LinearOpMode linearOpMode) {
        linearOpMode.telemetry.addData("Time", System.currentTimeMillis());
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }
}
