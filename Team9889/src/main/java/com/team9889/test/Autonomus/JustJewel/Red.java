package com.team9889.test.Autonomus.JustJewel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.JewelHitColor;

/**
 * Created by joshua9889 on 11/25/2017.
 */

@Autonomous(name = "RED FAIL PROOF")
@Disabled
public class Red extends AutoModeBase{
    @Override
    public void runOpMode(){
        waitForStart(true);
        runAction(new JewelHitColor(JewelColor.Red, this));
        sleep(5000);
    }
}
