package com.team9889.auto.actions.Drive;

import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.auto.actions.Action;
import com.team9889.lib.CruiseLib;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 4/17/2017.
 */

public class TurnToAngle implements Action {

    private double wantedAngle;

    private boolean pid;

    // PID STUFF
    private double error = 3;
    private double error_prior = 0;

    // Proportional gain
    private double kP =6.1;

    // Integral gain
    private double kI = 0;

    // Derivative gain
    private double kD = 160;

    private double count = 0;

    private double integral=0;
    private long iteration_time = 10; // Milli

    private double leftPow, rightPow;


    // BANG-BANG STUFF
    private int timesRun = 0;

    private Drive mDrive = Robot.getInstance().getDrive();

    /**
     *
     * @param angle Desired angle in degrees
     *              Positive angle is left
     *              Negative angle is right
     */
    public TurnToAngle(int angle){
        this.wantedAngle = angle;
        this.pid = true;
    }

    public TurnToAngle(int angle, boolean pid){
        this.wantedAngle = angle;
        this.pid = pid;
    }


    @Override
    public void start() {
        RobotLog.a("Start TurnToAngle");
        mDrive.DriveControlState(Drive.DriveControlStates.SPEED);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
    }

    @Override
    public void update() {
        if(pid){
            if(wantedAngle==180||wantedAngle==-180){
                if(mDrive.getGyroAngleDegrees()>0){
                    mDrive.setVelocityTarget(-Math.PI/3, Math.PI/3);
                } else if(mDrive.getGyroAngleDegrees()<0){
                    mDrive.setVelocityTarget(Math.PI/3, -Math.PI/3);
                } else {
                    mDrive.setVelocityTarget(0, 0);
                }
            } else {
                error = mDrive.getGyroAngleRadians() - CruiseLib.degreesToRadians(wantedAngle);
                integral = integral + (error*iteration_time);
                double derivative = (error - error_prior)/iteration_time;

                leftPow = (error * kP) + (integral * kI) + (derivative * kD);
                rightPow = -leftPow;

                if(Math.abs(leftPow)<0.4){
                    if(leftPow<0) {
                        leftPow = -0.4;
                        rightPow = -leftPow;
                    } else {
                        leftPow = 0.4;
                        rightPow = -leftPow;
                    }
                } else if(Math.abs(leftPow)>10){
                    if(leftPow<0) {
                        leftPow = -10;
                        rightPow = -leftPow;
                    } else {
                        leftPow = 10;
                        rightPow = -leftPow;
                    }
                }

                mDrive.setVelocityTarget(leftPow, rightPow);
                error_prior = error;

                try {
                    Thread.sleep(iteration_time);
                } catch (Exception e){}
                RobotLog.a("E:"+String.valueOf(CruiseLib.radianToDegrees(error))+"| Left: " + String.valueOf(mDrive.getLeftVelocity())+ "| Right: " +String .valueOf(mDrive.getRightVelocity()));
            }
        } else {
            if (wantedAngle<0){
                switch (timesRun){
                    case 0:
                        // Turn 90 degrees
                        mDrive.setLeftRightPower(0.6, -0.6);

                        if (mDrive.getGyroAngleDegrees() < wantedAngle)
                            timesRun++;
                        break;
                    case 1:
                        mDrive.setLeftRightPower(-0.35, 0.35);

                        if (mDrive.getGyroAngleDegrees() > wantedAngle)
                            timesRun++;
                        break;
                }
            } else if(wantedAngle>0){
                switch (timesRun) {
                    case 0:
                        // Turn 90 degrees
                        mDrive.setLeftRightPower(-0.6, 0.6);

                        if (mDrive.getGyroAngleDegrees() > wantedAngle)
                            timesRun++;
                        break;
                    case 1:
                        mDrive.setLeftRightPower(0.35, -0.35);

                        if (mDrive.getGyroAngleDegrees() < wantedAngle)
                            timesRun++;
                        break;
                }
            } else if(wantedAngle==0 && mDrive.getGyroAngleDegrees()<0){
                switch (timesRun) {
                    case 0:
                        // Turn 90 degrees
                        mDrive.setLeftRightPower(0.6, -0.6);

                        if (mDrive.getGyroAngleDegrees() < 0)
                            timesRun++;
                        break;
                    case 1:
                        mDrive.setLeftRightPower(-0.35, 0.35);

                        if (mDrive.getGyroAngleDegrees() > 0)
                            timesRun++;
                        break;
                }
            } else if(wantedAngle==0 && mDrive.getGyroAngleDegrees()>0){
                switch (timesRun) {
                    case 0:
                        // Turn 90 degrees
                        mDrive.setLeftRightPower(-0.6, 0.6);

                        if (mDrive.getGyroAngleDegrees() < 0)
                            timesRun++;
                        break;
                    case 1:
                        mDrive.setLeftRightPower(0.35, -0.35);

                        if (mDrive.getGyroAngleDegrees() > 0)
                            timesRun++;
                        break;
                }
            }

        }
    }

    @Override
    public boolean isFinished() {
        if(pid){
            if(wantedAngle==180||wantedAngle==-180){
                return Math.abs(mDrive.getGyroAngleDegrees())>178;
            } else {
                if(Math.abs(error)<CruiseLib.degreesToRadians(2))
                    count++;
                return count>1;
            }
        } else {
            return timesRun>1;
        }

    }

    @Override
    public void done() {
        mDrive.DriveControlState(Drive.DriveControlStates.POWER);
        mDrive.setLeftRightPower(0,0);
        RobotLog.a(String.valueOf(mDrive.getGyroAngleDegrees()));
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {}
        RobotLog.a("End of TurnToAngle");
    }

}
