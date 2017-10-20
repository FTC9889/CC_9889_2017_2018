package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.team9889.lib.CruiseLib.power3MaintainSign;

/**
 * Created by joshua9889 on 10/19/2017.
 */

@TeleOp(name = "TestMRTeleop")
public class TestMRDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor left = hardwareMap.dcMotor.get("left");
        DcMotor right = hardwareMap.dcMotor.get("right");
        left.setPower(0);
        right.setPower(0);
        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            double leftspeed, rightspeed, xvalue, yvalue;

            //Values from gamepads with modifications
            xvalue = power3MaintainSign(-gamepad1.left_stick_y);
            yvalue = -power3MaintainSign(gamepad1.right_stick_x);

            //Values to output to motors
            leftspeed =  yvalue - xvalue;
            rightspeed = yvalue + xvalue;


            //Set Motor Speeds
            left.setPower(leftspeed);
            right.setPower(rightspeed);
            idle();
        }
    }
}
