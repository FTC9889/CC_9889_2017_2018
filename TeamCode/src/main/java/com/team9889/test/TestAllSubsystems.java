package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 2/19/2018.
 */

@TeleOp
public class TestAllSubsystems extends Team9889Linear {
    Robot Robot = new Robot();
    @Override
    public void runOpMode() throws InterruptedException {
        Robot.init(this, true);
        telemetry.addData("Waiting for Start","");
        telemetry.update();
        waitForStart();

        Robot.testAllSubsystems(telemetry);
        telemetry.addData("Test Complete", "");
        telemetry.update();
        sleep(50000);

    }
}
