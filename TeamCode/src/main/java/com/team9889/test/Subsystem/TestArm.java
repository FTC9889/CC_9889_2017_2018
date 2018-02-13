package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;

//import org.openftc.hardware.rev.motorStuff.OpenDcMotor;

/**
 * Created by joshua9889 on 2/4/2018.
 */
@TeleOp
@Disabled
public class TestArm extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
//        OpenDcMotor extender = hardwareMap.get(OpenDcMotor.class, Constants.kRelicMotor);
//        CRServo vexMotor = hardwareMap.get(CRServo.class, Constants.kVexMotor);
//        Servo finger = hardwareMap.get(Servo.class, Constants.kFinger);
//        vexMotor.setPower(0.0);
//        finger.setPosition(1.0);
//        extender.setPower(0);
//        waitForStart();
//
//        while (opModeIsActive()){
//            if (extender.getCurrentDraw().doubleValue>3500)
//                extender.setPower(0.0);
//            else
//                extender.setPower(gamepad1.left_stick_y);
//
//
//
//            vexMotor.setPower(gamepad1.right_stick_y*80/100);
//            if(gamepad1.a)
//                finger.setPosition(0.2);
//            else if(gamepad1.b)
//                finger.setPosition(1.0);
//
//        }

        requestOpModeStop();
    }
}
