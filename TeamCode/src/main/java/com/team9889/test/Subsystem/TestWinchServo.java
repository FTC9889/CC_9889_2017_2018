package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;

/**
 * Created by joshua9889 on 12/9/2017.
 */

@TeleOp
@Disabled
public class TestWinchServo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo elbow = hardwareMap.get(Servo.class, Constants.kElbowServo);
        Servo finger = hardwareMap.get(Servo.class, Constants.kFinger);
        DcMotorEx winch = hardwareMap.get(DcMotorEx.class, Constants.kRelicMotor);

        double oneRotation = 1.0/7.5;
        double oneDegree = oneRotation/360;

        winch.setPower(0.1);
        sleep(1000);
        winch.setPower(0.0);

        winch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        winch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        winch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        winch.setPower(0);
        winch.setTargetPosition(0);

        elbow.setDirection(Servo.Direction.REVERSE);
        elbow.setPosition(oneDegree*280);

        finger.setDirection(Servo.Direction.REVERSE);
        finger.setPosition(0.0);

        waitForStart();

        while(opModeIsActive()){
            if(gamepad1.a)
                elbow.setPosition(oneDegree*160);
            else
                elbow.setPosition(0.0);

            if (gamepad1.right_bumper)
                finger.setPosition(1);
            else
                finger.setPosition(0);

            if(gamepad1.a){
                winch.setPower(1);
                winch.setTargetPosition(-2697);
            } else if(gamepad1.b){
                winch.setPower(1);
                winch.setTargetPosition(-8502);
            } else if(gamepad1.x){
                winch.setPower(1);
                winch.setTargetPosition(-874);
            }


            telemetry.addData("winch pos", winch.getCurrentPosition());
            //elbow.setPosition(Math.abs(gamepad1.left_trigger-1));
            telemetry.addData("pos",elbow.getPosition());
            telemetry.update();
        }

    }
}
