package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {
    private double currentAngle;
    private double wantedAngle;
    private double mError;
    private double mTolerance;
    boolean finished = false;
    private double mSpeed;
    private int timesRun = 0;

    private Drive mDrive;

    /**
     *
     * @param angle Desired angle
     *              Positive angle is left
     *              Negative angle is right
     * @param speed Speed of turn
     */
    public TurnToAngle(int angle, double speed){
        new TurnToAngle(angle, speed, 2);
    }

    /**
     * @param angle Desired angle
     *              Positive angle is left
     *              Negative angle is right
     * @param speed Speed of turn
     * @param tolerance Angle
     */
    public TurnToAngle(int angle, double speed, double tolerance){
        wantedAngle = angle;
        mSpeed = speed;
        mTolerance = tolerance;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {
        currentAngle = mDrive.getGyroAngleDegrees();
        mError = wantedAngle - currentAngle;

        switch (timesRun){
            case 0:

                break;
            case 1:

                break;
            case 2:

                break;
            case 3:
                finished = true;
                break;
        }
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mDrive = opMode.Robot.getDrive();
        mDrive.setLeftRightPower(0,0);
        mDrive.DriveControlState(Drive.DriveControlStates.POWER);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }
}
