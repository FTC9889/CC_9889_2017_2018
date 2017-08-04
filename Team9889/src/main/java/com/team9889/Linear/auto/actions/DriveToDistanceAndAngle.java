package com.team9889.Linear.auto.actions;

import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by lego on 8/4/2017.
 */

public class DriveToDistanceAndAngle implements Action {
    private int InitAngle;
    private double InitLeftMotorPosition;
    private double InitRightMotorPosition;

    public DriveToDistanceAndAngle(double Distance, double Angle, double RadiusInDegrees){

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        InitAngle = opMode.mDrive.getGyroAngleDegrees();
        InitLeftMotorPosition = opMode.mDrive.getLeftDistanceInches();
        InitRightMotorPosition = opMode.mDrive.getRightDistanceInches();
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }
}
