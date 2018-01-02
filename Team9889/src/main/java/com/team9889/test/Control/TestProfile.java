package com.team9889.test.Control;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 12/29/2017.
 */

@TeleOp
@Disabled
public class TestProfile extends Team9889Linear {

    private ElapsedTime speedTime = new ElapsedTime();
    private int lastLeftPos, lastRightPos;
    private double leftSpeed, rightSpeed;
    private double leftSpeedGood, rightSpeedGood;
    private double wantedSpeed = 10;
    private double left = 0.1, right = 0.1;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);
        Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);
        Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);

        lastLeftPos = Robot.getDrive().getLeftTicks();
        lastRightPos = Robot.getDrive().getRightTicks();
        speedTime.reset();

        // Thread For updating speed of robot.
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while(opModeIsActive()){
//                     Calculated speed in TicksPerMillisecond
//                    double currentTime = speedTime.nanoseconds()/1_000_000;
//
//                    leftSpeed = (Robot.getDrive().getLeftTicks() - lastLeftPos)/currentTime;
//                    rightSpeed = (Robot.getDrive().getRightTicks() - lastRightPos)/currentTime;
//
//                     Seconds
//                    leftSpeed = leftSpeed/1000;
//                    rightSpeed = rightSpeed/1000;
//
//                     Rotations on output shaft
//                    leftSpeed = leftSpeed/1120;
//                    rightSpeed = rightSpeed/1120;
//
//                     Rotations to degrees
//                    leftSpeed = leftSpeed/360;
//                    rightSpeed = rightSpeed/360;
//
//                     Degrees to Radians
//                    leftSpeed = CruiseLib.degreesToRadians(leftSpeed);
//                    rightSpeed = CruiseLib.degreesToRadians(rightSpeed);
//
//                    lastLeftPos = Robot.getDrive().getLeftTicks();
//                    lastRightPos = Robot.getDrive().getRightTicks();
//                    speedTime.reset();
//                }
//            }
//        }).start();

        while(opModeIsActive()){
            //Robot.getDrive().setLeftRightPower(gamepad1.left_stick_y, gamepad1.right_stick_y);

            //Robot.getDrive().setLeftRightPower(-0.4, -0.4);
            Robot.getDrive().setVelocityTarget(3*Math.PI/2, 3*Math.PI/2);
            //telemetry.addData("Gamepad1 left Y", gamepad1.left_stick_y);
            //print("Hello");

            telemetry.addData("Left Power", Robot.getDrive().leftMaster_.getPower());
            telemetry.addData("Right Power", Robot.getDrive().rightMaster_.getPower());
            telemetry.addData("Left Vel", Robot.getDrive().getLeftVelocity());
            telemetry.addData("Right Vel", Robot.getDrive().getRightVelocity());
            //telemetry.addData("Left Speed", leftSpeed);
            //telemetry.addData("Right Speed", rightSpeed);
            telemetry.update();
            //RobotLog.d("-Left Vel: "+ String.valueOf(Robot.getDrive().getLeftVelocity()));
            //RobotLog.d("-Right Vel: " + String.valueOf(Robot.getDrive().getRightVelocity()));
            //RobotLog.d("--------------------Right Power: " + String.valueOf(Robot.getDrive().leftMaster_.getPower()));
            //RobotLog.d("--------------------Left Power: " + String.valueOf(-Robot.getDrive().rightMaster_.getPower()));
        }


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (opModeIsActive()){
//                    telemetry.addData("Left", (Robot.getDrive().getLeftTicks() - lastLeftPos));
//                    telemetry.addData("Right", (Robot.getDrive().getRightTicks() - lastRightPos));
//                    telemetry.addData("Left Power", left);
//                    telemetry.addData("Right Power", right);
//                    telemetry.addData("Left Speed", leftSpeed);
//                    telemetry.addData("Right Speed", rightSpeed);
//                    telemetry.update();
//                }
//            }
//        }).start();
//
//        lastLeftPos = Robot.getDrive().getLeftTicks();
//        lastRightPos = Robot.getDrive().getRightTicks();
//        speedTime.reset();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (opModeIsActive()){
//                    telemetry.addData("left wanted vel", left);
//                    telemetry.addData("right wanted vel", right);
//                    telemetry.addData("Left Vel", Robot.getDrive().getLeftVelocity());
//                    telemetry.addData("Right Vel", Robot.getDrive().getRightVelocity());
//                    telemetry.addData("Left pos", Robot.getDrive().getLeftDistanceInches());
//                    telemetry.addData("Right pos", Robot.getDrive().getRightDistanceInches());
//                    telemetry.update();
//                }
//            }
//        }).start();

        left = 1;
        right = 1;
        int i = 0;
        boolean s = true;
        while(opModeIsActive()){// && Robot.getDrive().getLeftDistanceInches()<10){
//            if(Robot.getDrive().getLeftVelocity()<10) {
//                left += 0.1;
//                right += 0.1;
//            } else {
//                i++;
//            }
//            if(gamepad1.a && s){
//                left -= 1;
//                right -= 1;
//                s = false;
//            } else {
//                s = true;
//            }
//
//            if(gamepad1.y && s){
//                left += 1;
//                right += 1;
//                s = false;
//            } else {
//                s = true;
//            }


            Robot.getDrive().setLeftRightPower(gamepad1.left_stick_y,gamepad1.left_stick_y);
            //Robot.getDrive().setVelocityTarget(left, right);
        }

//        telemetry.addData("Stage", 1);
//        telemetry.update();
//        Robot.getDrive().setVelocityTarget(100, 100);
//        Robot.getDrive().setLeftRightPower(1.0, 1.0);
//        sleep(500);
//        Robot.getDrive().setVelocityTarget(0,0);
//        sleep(1000);
//
//        telemetry.addData("Stage", 2);
//        telemetry.update();
//        Robot.getDrive().setVelocityTarget(500, 0);
//        sleep(500);
//        Robot.getDrive().setVelocityTarget(0,0);
//        sleep(1000);
//
//        telemetry.addData("Stage", 3);
//        telemetry.update();
//        Robot.getDrive().setVelocityTarget(0, 500);
//        sleep(500);
//        Robot.getDrive().setVelocityTarget(0,0);
//        sleep(1000);

//        while(opModeIsActive()){
//            if(leftSpeed<wantedSpeed){
//                left += 0.02;
//            } else if(leftSpeed>wantedSpeed){
//                left = 0.3;
//            }
//
//            if(rightSpeed<wantedSpeed) {
//                right +=0.02;
//            } else if(rightSpeed>wantedSpeed){
//                right = 0.3;
//            }


//        }

    }
}
