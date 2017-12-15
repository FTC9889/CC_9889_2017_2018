package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;

/**
 * Created by joshua9889 on 9/10/2017.
 */

@Autonomous(name = "Null OpMode")
@Disabled
public class NullOpMode extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(this, true);
    }
}
