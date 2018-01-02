package com.team9889.auto.modes.MultiGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class RED_FOWARD_TWO_GLYPH {
    public RED_FOWARD_TWO_GLYPH(AutoModeBase M, RelicRecoveryVuMark column){
        M.runAction(new DriveToDistance(10, 0));

        // Turn to the cb
        M.runAction(new TurnRightMotor(-90));

        M.runAction(new GlyphDeployToFirstLevel());
        M.runAction(new DriveToDistance(10, -90));
        M.runAction(new GlyphRelease());
    }
}
