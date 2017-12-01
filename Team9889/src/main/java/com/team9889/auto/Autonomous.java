package com.team9889.auto;

import com.team9889.auto.actions.JewelHitColor;
import com.team9889.auto.modes.BLUE_BACK;
import com.team9889.auto.modes.BLUE_FOWARD;
import com.team9889.auto.modes.RED_BACK;
import com.team9889.auto.modes.RED_FOWARD;

/**
 * Created by Jin on 11/10/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends AutoModeBase {

    RED_BACK red_back = new RED_BACK();
    RED_FOWARD red_foward = new RED_FOWARD();
    BLUE_BACK blue_back = new BLUE_BACK();
    BLUE_FOWARD blue_foward = new BLUE_FOWARD();

    @Override
    public void runOpMode() {

        waitForTeamStart(this, true);

        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red));
        else if (alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue));

        if(!getPitGlyph){
            if (alliance == "Red" && frontBack == "Back") {
                red_back.runOpMode(this, WhatColumnToScoreIn());
            }
            else if (alliance == "Red" && frontBack == "Front") {
                red_foward.runOpMode(this, WhatColumnToScoreIn());
            }
            else if (alliance == "Blue" && frontBack == "Back") {
                blue_back.runOpMode(this, WhatColumnToScoreIn());
            }
            else if (alliance == "Blue" && frontBack == "Front") {
                blue_foward.runOpMode(this, WhatColumnToScoreIn());
            }
        } else {

        }

        Robot.getJewel().stop();
        sleep(1000);

        finalAction();
    }


}
