package com.team9889.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.JewelHitColor;

/**
 * Created by joshua9889 on 11/25/2017.
 */

@Autonomous(name = "Blue FAIL PROOF")
public class Blue extends AutoModeBase{
    @Override
    public void runOpMode(){
        waitForTeamStart(this, true);
        runAction(new JewelHitColor(JewelColor.Blue));
        sleep(1000);
        Robot.getJewel().stop();
        sleep(1000);
    }
}
