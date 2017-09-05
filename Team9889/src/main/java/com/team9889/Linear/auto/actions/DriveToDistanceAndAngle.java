package com.team9889.Linear.auto.actions;

import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by lego on 8/4/2017.
 */

public class DriveToDistanceAndAngle implements Action {
    private int mInitAngle;
    private double mInitLeftMotorPosition, mInitRightMotorPosition;
    private double mCalulatedLeftMotorPosition, mCalulatedRightMotorPosition;

    private double mWantedDistance, mWantedAngle, mRadiusOfTurn;

    public DriveToDistanceAndAngle(double Distance, double Angle, double RadiusInDegrees){
        mWantedDistance = Distance;
        mWantedAngle = Angle;
        mRadiusOfTurn = RadiusInDegrees;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mInitAngle = opMode.mSuperstructure.getDrive().getGyroAngleDegrees();
        mInitLeftMotorPosition = opMode.mSuperstructure.getDrive().getLeftDistanceInches();
        mInitRightMotorPosition = opMode.mSuperstructure.getDrive().getRightDistanceInches();

        mRadiusOfTurn = mRadiusOfTurn*(Math.PI/180);

    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }
}