package com.team9889.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.subsystems.Drive;

/**
 * Created by Joshua H on 4/16/2017.
 */

public class DriveUntilInRangeAction implements Action {
    private double LstartingDistance;
    private double RstartingDistance;
    private double mMinWantedDistance;
    private double mMaxWantedDistance;

    private double CLmWantedDistance;
    private double CRmWantedDistance;

    private double mSpeed;

    private Drive mDrive = Drive.getInstance();

    public DriveUntilInRangeAction(double velocity, double minDistanceAway, double maxDistanceAway){
        mSpeed = velocity;
        mMinWantedDistance = minDistanceAway;
        mMaxWantedDistance = maxDistanceAway;
    }

    @Override
    public void start(HardwareMap hardwareMap) {
        mDrive.init(hardwareMap, false);
        LstartingDistance = mDrive.getLeftDistanceInches();
        RstartingDistance = mDrive.getRightDistanceInches();
    }

    @Override
    public void update(LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
