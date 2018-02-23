package com.team9889.auto;

import com.team9889.auto.actions.Jewel.JewelHitColor;
import com.team9889.auto.modes.MultiGlyph.TwoGlyph.BLUE_BACK_TWO_GLYPH;
import com.team9889.auto.modes.MultiGlyph.TwoGlyph.BLUE_FOWARD_TWO_GLYPH;
import com.team9889.auto.modes.MultiGlyph.TwoGlyph.RED_BACK_TWO_GLYPH;
import com.team9889.auto.modes.MultiGlyph.TwoGlyph.RED_FOWARD_TWO_GLYPH;
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
        waitForStart(true);

        if(opModeIsActive() && !isStopRequested()){
            // Jewel thing
            if(alliance == "Red")
                runAction(new JewelHitColor(JewelColor.Red, this));
            else if (alliance == "Blue")
                runAction(new JewelHitColor(JewelColor.Blue, this));

            // Real Code for moving
            // THEY ALL SCORE GLYPHS!!!
            if (alliance == "Red" && frontBack == "Back") {
//                if(getPitGlyph)
//                    new RED_BACK_TWO_GLYPH(this, WhatColumnToScoreIn());
//                else
                    new RED_BACK(this, WhatColumnToScoreIn());
            }
            else if (alliance == "Red" && frontBack == "Front") {
                new RED_FOWARD_TWO_GLYPH(this, WhatColumnToScoreIn());
            }
            else if (alliance == "Blue" && frontBack == "Back") {
//                if(getPitGlyph)
//                    new BLUE_BACK_TWO_GLYPH(this, WhatColumnToScoreIn());
//                else
                    new BLUE_BACK(this, WhatColumnToScoreIn());
            }
            else if (alliance == "Blue" && frontBack == "Front") {
                new BLUE_FOWARD_TWO_GLYPH(this, WhatColumnToScoreIn());
            }
        }
    }


}
