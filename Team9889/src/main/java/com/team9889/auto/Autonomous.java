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

    @Override
    public void runOpMode() {
        waitForTeamStart(this, true);

        // Jewel thing
        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red));
        else if (alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue));

        // Real Code for moving
        // THEY ALL SCORE GLYPHS!!!
        if (alliance == "Red" && frontBack == "Back") {
            new RED_BACK(this, WhatColumnToScoreIn());
        }
        else if (alliance == "Red" && frontBack == "Front") {
            new RED_FOWARD(this, WhatColumnToScoreIn());
        }
        else if (alliance == "Blue" && frontBack == "Back") {
            new BLUE_BACK(this, WhatColumnToScoreIn());
        }
        else if (alliance == "Blue" && frontBack == "Front") {
            new BLUE_FOWARD(this, WhatColumnToScoreIn());
        }

        Robot.getJewel().stop();
        sleep(1000);

        finalAction();
    }


}
