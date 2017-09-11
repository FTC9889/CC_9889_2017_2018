package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.VuMark;

/**
 * Created by joshua9889 on 9/10/2017.
 */

@Autonomous(name = "test Vuforia")
//@Disabled
public class TestVuforia extends Team9889LinearOpMode {
    VuMark vuMark = new VuMark();

    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(this, true);
    }
}
