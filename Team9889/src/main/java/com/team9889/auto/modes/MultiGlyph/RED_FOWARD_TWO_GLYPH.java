package com.team9889.auto.modes.MultiGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class RED_FOWARD_TWO_GLYPH {
    public RED_FOWARD_TWO_GLYPH(AutoModeBase M, RelicRecoveryVuMark column){
        // Drive off Balance Stone
        M.runAction(new DriveToDistance(17, 0.0, 0.1));
        M.sleep(250);

        // Turn to the cb
        M.runAction(new TurnToAngle(-90, 0.6));
        M.runAction(new DriveToDistance(16, -90, 0.3));
        M.runAction(new GlyphDeployToFirstLevel());

        M.runAction(new TurnToAngle(-110, 0.6));
        M.runAction(new DriveToDistance(10, -110, 0.2));
        M.runAction(new DriveTimeAction(200, -0.3));
        M.sleep(100);
        M.runAction(new GlyphRelease());

        M.runAction(new DriveTimeAction(500, -0.2));
        M.runAction(new GlyphRetractArm());
        M.runAction(new IntakeDeployWideIntake());
        M.runAction(new TurnToAngle(65, 0.6));
        M.runAction(new DriveToDistance(30, 65, 0.6));
        M.sleep(500);
        M.runAction(new TurnToAngle(50, 1.0));
        M.sleep(500);
        M.runAction(new DriveToDistance(5, 50, 1.0));
        M.sleep(500);
        M.runAction(new DriveTimeAction(700, -0.1));
        M.runAction(new TurnToAngle(-90, 0.6));
        M.runAction(new DriveToDistance(14, -90, 0.4));

        M.sleep(1000);
    }
}
