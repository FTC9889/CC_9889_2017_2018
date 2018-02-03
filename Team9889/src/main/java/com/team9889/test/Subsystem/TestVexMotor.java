package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;

import org.openftc.hardware.rev.motorStuff.OpenDcMotor;

/**
 * Created by joshua9889 on 1/26/2018.
 */

@TeleOp
public class TestVexMotor extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        final Servo servo = hardwareMap.get(Servo.class, Constants.kLargeServo);
        OpenDcMotor relicMotor = hardwareMap.get(OpenDcMotor.class, Constants.kRelicMotor);
        relicMotor.setPower(0.0);
        relicMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        relicMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        relicMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (opModeIsActive())
                    servo.setPosition(0.7);
            }
        }).start();


        waitForStart();

        while (opModeIsActive()){
            relicMotor.setPower(gamepad1.right_stick_y);
            telemetry.addData("Ticks", relicMotor.getCurrentPosition());
            telemetry.addData("Draw",relicMotor.getCurrentDraw().doubleValue);
            telemetry.update();
        }

    }
}
