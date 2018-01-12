package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

import static java.lang.Math.*;

/**
 * Created by joshua9889 on 12/30/2017.
 * OpMode to test stuff
 */

@Autonomous
@Disabled
public class FixBrokenThings extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

        // Hit Jewel off
        jewel_Color = JewelColor.Blue;
        runAction(new JewelHitColor(JewelColor.Blue));




    }
}
