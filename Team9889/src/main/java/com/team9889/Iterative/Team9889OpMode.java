package com.team9889.Iterative;

import com.team9889.Iterative.subsystems.Drive;

import camera_opmodes.OpModeCamera;

import static com.team9889.lib.CruiseLib.calcLeftTankDrive;
import static com.team9889.lib.CruiseLib.calcRightTankDrive;

/**
 * Created by Joshua on 7/29/2017.
 */

public class Team9889OpMode extends OpModeCamera {

    public Drive mDrive_ = Drive.getInstance();

    @Override
    public void init() {
        mDrive_.init(this);
        super.init();
    }

    @Override
    public void stop(){
        mDrive_.stop(this);
    }

    Thread DriveSteerThread = new Thread(new Runnable() {
        @Override
        public void run() {
            double leftspeed, rightspeed, xvalue, yvalue, div;

            //Used to lower the max speed of the robot when lining up for shooting/beacons
            if (gamepad1.left_trigger > 0.3){
                div = 4;
            }else {
                div = 1;
            }

            //Values from gamepads with modifications
            xvalue = gamepad1.right_stick_x/div;
            yvalue = gamepad1.left_stick_y/div;

            //Values to output to motors
            leftspeed = calcLeftTankDrive(xvalue, yvalue);
            rightspeed = calcRightTankDrive(xvalue, yvalue);

            //Set Motor Speeds
            mDrive_.setLeftRightPower(leftspeed, rightspeed);
        }
    });
}
