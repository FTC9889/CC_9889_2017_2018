package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.Team9889Linear;

/**
 * Created by Joshua H on 2/21/2018.
 */

@TeleOp
@Disabled
public class TestClawServo extends Team9889Linear {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(false, false);

        while(opModeIsActive()){
            Robot.getRelic().openFinger();
            sleep(1000);
            Robot.getRelic().closeFinger();
            sleep(1000);
        }


    }
}