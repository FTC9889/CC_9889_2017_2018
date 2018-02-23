package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 2/19/2018.
 */

@TeleOp(name = "Test Subsystems for Errors")
@Disabled
public class TestAllSubsystems extends Team9889Linear {
    Robot Robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.init(this, true);
        telemetry.addData("Waiting for Start","");
        telemetry.update();
        waitForStart();

        while (opModeIsActive())
            Robot.getRelic().elbowDeploy();

    }
}
