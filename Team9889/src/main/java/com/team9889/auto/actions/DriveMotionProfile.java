package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

/**
 * See https://www.youtube.com/watch?v=8319J1BEHwM&feature=youtu.be
 * This class does NOT control the pid or speed of the motors directly.
 * Created by joshua9889 on 12/29/2017.
 */

public class DriveMotionProfile implements Action {

    private Robot robot = Robot.getInstance();
    // Drivetrain object
    private Drive mDrive = robot.getDrive();

    // is in ticks
    private int distance;
    private double cruiseVel;

    private int lastLeftPos, lastRightPos;
    private double leftSpeed, rightSpeed;
    private double leftWantedSpeed, rightWantedSpeed;

    private int timeFromZeroToCruiseVel; // Milli
    private int timeFromCruiseVelToZero;
    private int timeToCruise;

    private int startTime, currentTime, timeToRun;
    private ElapsedTime motionTime = new ElapsedTime();
    private ElapsedTime speedTime = new ElapsedTime();

    private double[] timeToDistanceArray;

    /**
     * @param distance Distance in encoder ticks
     * @param TimeToCruise Time from Zero to Cruise VEL
     * @param TimeToZero Time from Cruise VEL to Zero
     * @param timeToRun Time to execute in Milliseconds
     */
    public DriveMotionProfile(int distance,
                              double cruiseVel,
                              int TimeToCruise,
                              int TimeToZero,
                              int timeToRun){
        this.distance = distance;
        this.cruiseVel = cruiseVel;
        this.timeFromZeroToCruiseVel = TimeToCruise;
        this.timeFromCruiseVelToZero = TimeToZero;
        this.timeToRun = timeToRun;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start() {
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        // Used to calculate current speed
        lastLeftPos = mDrive.getLeftTicks();
        lastRightPos = mDrive.getRightTicks();

        // Used to find how much time we have to stay at the cruise velocity.
        timeToCruise = timeToRun - (timeFromZeroToCruiseVel+timeFromCruiseVelToZero);

        // Make an array that holds the positions with an index of the current time in milliseconds.
        timeToDistanceArray = new double[timeToRun];

        // Used to calculate the Distances and times of the robot
        for (int t=0;t<timeToDistanceArray.length;t++){
            if(t<timeFromZeroToCruiseVel){

            } else if(t<timeFromZeroToCruiseVel+timeToCruise){

            } else if(t>timeFromZeroToCruiseVel+timeToCruise){

            }
        }

        startTime = (int)motionTime.milliseconds();
        speedTime.reset();
    }

    @Override
    public void update() {
        // Used for determining where we need to be.
        currentTime = (int)motionTime.milliseconds() - startTime;

        // Calculated speed in TicksPerMillisecond
        leftSpeed = (mDrive.getLeftTicks() - lastLeftPos)/speedTime.milliseconds();
        rightSpeed = (mDrive.getRightTicks() - lastRightPos)/speedTime.milliseconds();

        lastLeftPos = mDrive.getLeftTicks();
        lastRightPos = mDrive.getRightTicks();
        speedTime.reset();
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
    }
}
