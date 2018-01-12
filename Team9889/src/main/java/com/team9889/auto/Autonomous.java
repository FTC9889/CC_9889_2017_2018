package com.team9889.auto;

import com.team9889.auto.actions.JewelHitColor;
import com.team9889.auto.modes.MultiGlyph.BLUE_FOWARD_TWO_GLYPH;
import com.team9889.auto.modes.MultiGlyph.RED_FOWARD_TWO_GLYPH;
import com.team9889.auto.modes.SingleGlyph.BLUE_BACK;
import com.team9889.auto.modes.SingleGlyph.BLUE_FOWARD;
import com.team9889.auto.modes.SingleGlyph.RED_BACK;
import com.team9889.auto.modes.SingleGlyph.RED_FOWARD;

/**
 * Created by Jin on 11/10/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends AutoModeBase {

    @Override
    public void runOpMode() {
        waitForStart(this, true);

        // Jewel thing
        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red, this));
        else if (alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue, this));

        // Real Code for moving
        // THEY ALL SCORE GLYPHS!!!
        if (alliance == "Red" && frontBack == "Back") {
            new RED_BACK(this, WhatColumnToScoreIn());
        }
        else if (alliance == "Red" && frontBack == "Front") {
            if(getPitGlyph)
                new RED_FOWARD_TWO_GLYPH(this, WhatColumnToScoreIn());
            else
                new RED_FOWARD(this, WhatColumnToScoreIn());
        }
        else if (alliance == "Blue" && frontBack == "Back") {
            new BLUE_BACK(this, WhatColumnToScoreIn());
        }
        else if (alliance == "Blue" && frontBack == "Front") {
            if (getPitGlyph)
                new BLUE_FOWARD_TWO_GLYPH(this, WhatColumnToScoreIn());
            else
                new BLUE_FOWARD(this, WhatColumnToScoreIn());
        }
    }


}
