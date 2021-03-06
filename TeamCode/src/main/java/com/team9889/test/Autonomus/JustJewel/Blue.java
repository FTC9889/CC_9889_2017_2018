package com.team9889.test.Autonomus.JustJewel;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Jewel.JewelHitColor;

/**
 * Created by joshua9889 on 11/25/2017.
 */

@Autonomous(name = "Blue FAIL PROOF")
@Disabled
public class Blue extends AutoModeBase{
    @Override
    public void runOpMode(){
        waitForStart(true);
        runAction(new JewelHitColor(JewelColor.Blue, this));
        sleep(5000);
    }
}
