package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 12/24/2017.
 */

public class TurnRightwithLeftMotor implements Action {
    private Drive mDrive;
    private double angle, speed;
    private int finish = 0;
    private boolean isFinished = false;

    public TurnRightwithLeftMotor(double angle, double speed){
        this.angle = angle;
        this.speed = Math.abs(speed);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();

        while(opMode.opModeIsActive() && !opMode.isStopRequested() && 5<(Math.abs(mDrive.getGyroAngleDegrees()-angle))){
            if(angle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(speed, 0.0);
            } else {
                mDrive.setLeftRightPower(-speed, 0.0);
            }
            Thread.yield();
        }
        mDrive.setLeftRightPower(0,0);

        try {Thread.sleep(100);} catch (InterruptedException e) {}

        while(opMode.opModeIsActive() && !opMode.isStopRequested() && 3<(Math.abs(mDrive.getGyroAngleDegrees()-angle))){
            if(angle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(speed/4, 0.0);
            } else {
                mDrive.setLeftRightPower(-speed/4, 0.0);
            }
            Thread.yield();
        }
        mDrive.setLeftRightPower(0,0);

        while(opMode.opModeIsActive() && !opMode.isStopRequested() && 1<(Math.abs(mDrive.getGyroAngleDegrees()-angle))){
            if(angle < mDrive.getGyroAngleDegrees()){
                mDrive.setLeftRightPower(speed/5, 0.0);
            } else {
                mDrive.setLeftRightPower(-speed/5, 0.0);
            }
            Thread.yield();
        }
        mDrive.setLeftRightPower(0,0);
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }
}
