package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class RED_FOWARD {

    public RED_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        // Determine what column to score the glpyh in
        switch (column){
            case LEFT:
                // Drive off balance stone
                M.runAction(new DriveToDistance(17, 0));
                M.runAction(new TurnToAngle(-90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(30, -91));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-6, -91));
                M.runAction(new DriveTimeAction(1000, 4*PI, -91));
                M.runAction(new DriveToDistance(-4, -91));
                break;
            case CENTER:
                // Turn to Center column
                M.runAction(new TurnToAngle(-125));

                // Drive foward to place glyph
                M.runAction(new DriveToDistance(10, -125));

                // Release glpyh
                M.runAction(new GlyphRelease());

                // Backup
                M.runAction(new DriveToDistance(-5, -125, Math.PI));

                // Retract everything
                M.runAction(new GlyphRetractArm());

                // RAM INTO THINGS!!
                M.runAction(new DriveToDistance(4, -125));
                M.runAction(new DriveToDistance(-2, -125, 3*Math.PI/2));

                break;
            case RIGHT:
                // Turn to Right column
                M.runAction(new TurnToAngle(-145));

                // Drive foward to place glyph
                M.runAction(new DriveToDistance(14,-145));
                M.runAction(new TurnToAngle(-140));

                // Release glpyh
                M.runAction(new GlyphRelease());

                // Backup
                M.runAction(new DriveToDistance(-5, -140, Math.PI));

                // Retract everything
                M.runAction(new GlyphRetractArm());

                // RAM INTO THINGS!!
                M.runAction(new DriveToDistance(4, -140));
                M.runAction(new DriveToDistance(-2, -140, 3*Math.PI/2));

                break;
            default:
                // Turn to Left column
                M.runAction(new TurnToAngle(-90));

                // Drive foward to place glpyh
                M.runAction(new DriveToDistance(4, -90));

                // Release glpyh
                M.runAction(new GlyphRelease());

                // Backup
                M.runAction(new DriveToDistance(-5, -90, Math.PI));

                // Retract everything
                M.runAction(new GlyphRetractArm());

                // RAM INTO THINGS!!
                M.runAction(new DriveToDistance(4, -90));
                M.runAction(new DriveToDistance(-2, -90, 3*Math.PI/2));
                break;
        }
    }
}
