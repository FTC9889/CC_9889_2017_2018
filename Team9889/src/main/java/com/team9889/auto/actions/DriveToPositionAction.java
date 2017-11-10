package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 10/5/2017.
 */

public class DriveToPositionAction implements Action {

    private Drive mDrive = null;
    private double left_power, right_power;
    private int left_pos, right_pos;
    private int tolerence;
    private int posLength;

    public DriveToPositionAction(int left_pos, int right_pos, double left_power, double right_power){
        new DriveToPositionAction(left_pos, right_pos, left_power, right_power, 10);
    }

    public DriveToPositionAction(int left_pos, int right_pos, double left_power, double right_power, int tolerence){
        this.left_pos = left_pos;
        this.right_pos = right_pos;
        this.left_power = left_power;
        this.right_power =  right_power;
        this.tolerence = tolerence;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(this.left_pos) - Math.abs(mDrive.leftMaster_.getCurrentPosition()) < tolerence
                &&
                Math.abs(this.right_pos) - Math.abs(mDrive.rightMaster_.getCurrentPosition()) < tolerence;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mDrive = opMode.Robot.getDrive();

        this.left_pos += mDrive.getLeftTicks();
        this.right_pos += mDrive.getRightTicks();

        opMode.InternalopMode.telemetry.addData("Started Path Following", "");
        opMode.InternalopMode.telemetry.update();
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        mDrive.DriveControlState(Drive.DriveControlStates.POSITION);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        mDrive.leftMaster_.setTargetPosition(left_pos);
        mDrive.rightMaster_.setTargetPosition(-right_pos);
        mDrive.setLeftRightPower(left_power, right_power);
    }

    @Override
    public void done() {
        mDrive.stop();
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }
}
