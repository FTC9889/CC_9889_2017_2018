package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.Team9889LinearOpMode;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.lib.VuMark;

/**
 * Created by joshua9889 on 9/10/2017.
 */

@Autonomous(name = "Null OpMode")
//@Disabled
public class NullOpMode extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(this, true);
        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red));
        else if(alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue));

        sleep(4000);
    }
}
