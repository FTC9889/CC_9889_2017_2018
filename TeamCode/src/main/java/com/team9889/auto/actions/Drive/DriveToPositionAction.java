package com.team9889.auto.actions.Drive;

import com.team9889.auto.actions.Action;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 10/5/2017.
 */

@Deprecated
public class DriveToPositionAction implements Action {

    // Drivetrain object
    private Drive mDrive = Robot.getInstance().getDrive();
    private double left_power, right_power;
    private int left_pos, right_pos;
    private int tolerance;

    public DriveToPositionAction(int left_pos, int right_pos, double left_power, double right_power){
        new DriveToPositionAction(left_pos, right_pos, left_power, right_power, 10);
    }

    public DriveToPositionAction(int left_pos, int right_pos, double left_power, double right_power, int tolerance){
        this.left_pos = left_pos;
        this.right_pos = right_pos;
        this.left_power = left_power;
        this.right_power =  right_power;
        this.tolerance = tolerance;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(this.left_pos) - Math.abs(mDrive.leftMaster_.getCurrentPosition()) < this.tolerance
                &&
                Math.abs(this.right_pos) - Math.abs(mDrive.rightMaster_.getCurrentPosition()) < this.tolerance;
    }

    @Override
    public void start() {
        this.left_pos += mDrive.getLeftTicks();
        this.right_pos += mDrive.getRightTicks();
    }

    @Override
    public void update() {
        mDrive.DriveControlState(Drive.DriveControlStates.POSITION);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        mDrive.leftMaster_.setTargetPosition(left_pos);
        mDrive.rightMaster_.setTargetPosition(right_pos);
        mDrive.setLeftRightPower(left_power, right_power);
    }

    @Override
    public void done() {
        mDrive.stop();
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }
}