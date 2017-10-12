package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.DriveControlStates;
import com.team9889.subsystems.DriveZeroPowerStates;

/**
 * Created by joshua9889 on 10/5/2017.
 */

public class DrivePathAction implements Action {

    private MRDrive mMRDrive = null;
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
            mMRDrive = opMode.mSuperstructure.getDrive();
            mMRDrive.DriveControlState(DriveControlStates.POSITION);
            mMRDrive.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);
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
        mMRDrive.stop();
        mMRDrive.DriveZeroPowerState(DriveZeroPowerStates.FLOAT);
    }
}
