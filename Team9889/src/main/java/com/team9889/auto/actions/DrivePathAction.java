package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 10/5/2017.
 */

public class DrivePathAction implements Action {

    private Drive mDrive = null;
    private double[] left_pos_, right_pos_, left_power_, right_power_;
    private int posLength;

    public DrivePathAction (double[] left_pos, double[] right_pos, double[] left_power, double[] right_power){
        this.left_pos_ = left_pos;
        this.right_pos_ = right_pos;
        this.left_power_ = left_power;
        this.right_power_ =  right_power;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        if((left_pos_.length == right_pos_.length)
                == (left_power_.length == right_power_.length)){
            mDrive = opMode.Robot.getDrive();
            mDrive.DriveControlState(Drive.DriveControlStates.POSITION);
            mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
            opMode.InternalopMode.telemetry.addData("Started Path Following", "");
            opMode.InternalopMode.telemetry.update();
        } else {
            opMode.InternalopMode.telemetry.addData("Wrong Length of array", "");
            opMode.InternalopMode.telemetry.update();
            opMode.InternalopMode.requestOpModeStop();
        }


    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {
        mDrive.stop();
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);
    }
}