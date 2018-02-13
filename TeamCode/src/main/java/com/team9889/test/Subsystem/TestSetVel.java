package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.team9889.Constants;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Created by joshua9889 on 12/30/2017.
 */

@Autonomous
@Disabled
public class TestSetVel extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {

        DcMotorEx Right = hardwareMap.get(DcMotorEx.class, Constants.kLeftDriveMasterId);
        DcMotorEx Left = hardwareMap.get(DcMotorEx.class, Constants.kRightDriveMasterId);

        while(opModeIsActive() && !isStarted() && Right.getCurrentPosition() != 0){
            Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        while(opModeIsActive() && !isStarted() && Left.getCurrentPosition() != 0){
            Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }
        Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Waiting for Start", "");
        telemetry.update();

        waitForStart();

        Left.setVelocity(Math.PI, AngleUnit.RADIANS);
        Right.setVelocity(-Math.PI, AngleUnit.RADIANS);

        while(opModeIsActive()){
            telemetry.addData("Left Vel", Left.getVelocity(AngleUnit.RADIANS));
            telemetry.addData("Left Power", Left.getPower());
            telemetry.addData("Right Vel", Right.getVelocity(AngleUnit.RADIANS));
            telemetry.addData("Right Power", Right.getPower());
            telemetry.update();
        }
    }
}
