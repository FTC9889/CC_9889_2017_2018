package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.lib.RevIMU;

/**
 * Created by joshua9889 on 11/21/2017.
 */

@TeleOp

public class TestingIMUCode extends LinearOpMode {
    RevIMU imu1;
    RevIMU imu2;
    @Override
    public void runOpMode() throws InterruptedException {
        imu1 = new RevIMU("imu 1", hardwareMap);
        imu2 = new RevIMU("imu", hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Heading 1", imu1.getHeading());
            telemetry.addData("Heading 2", imu2.getHeading());
            telemetry.addData("Average Heading", (imu1.getHeading()+imu2.getHeading())/2);
            telemetry.update();
        }


    }
}
