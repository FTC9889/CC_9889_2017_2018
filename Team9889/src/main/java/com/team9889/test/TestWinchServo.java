package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by joshua9889 on 12/9/2017.
 */

@TeleOp
public class TestWinchServo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo winch = hardwareMap.servo.get("ws");

        winch.setPosition(0.1);
        waitForStart();
        winch.setPosition(0.094);
        sleep(3000);

        while(opModeIsActive()){
            winch.setPosition(gamepad1.left_trigger);
            telemetry.addData("pos",winch.getPosition());
            telemetry.update();
        }

    }
}
