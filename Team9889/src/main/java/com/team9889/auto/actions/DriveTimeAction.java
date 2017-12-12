package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 5/5/17.
 */

public class DriveTimeAction implements Action {
    private double mSpeed;
    private int Milliseconds;
    private Drive mDrive;

    public DriveTimeAction(int milliseconds, double speed) {
        Milliseconds = milliseconds;
        mSpeed = speed;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889LinearOpMode  opMode) {
        mDrive = opMode.Robot.getDrive();
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.setLeftRightPower(mSpeed, mSpeed);
        opMode.sleep(Milliseconds);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {}

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }
}
