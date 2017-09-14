package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.lib.AutoTransitioner;

import org.firstinspires.ftc.robotcore.external.Func;

@Autonomous(name = "AutoTransitioningAuto")
@Disabled
public class AutoTransitioningAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Initializing Here", true);
        telemetry.update();


        AutoTransitioner.transitionOnStop(this, "Robot Teleop");
        // AutoTransitioner used before waitForStart()
        waitForStart();


        telemetry.addData("Timer", new Func<Double>() {
            @Override
            public Double value() {
                return getRuntime();
            }
        });
        while (opModeIsActive()) {
            telemetry.update();
        }
    }
}
