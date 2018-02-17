package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Jewel.JewelExtend;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class RED_FOWARD {

    public RED_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        M.runAction(new DriveToDistance(20, 0, Math.PI/2));

        // Determine what column to score the glyph in
        switch (column){
            case LEFT:
                M.runAction(new TurnToAngle(-93));
                M.runAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(25, -93, 2*Math.PI));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, -93, Math.PI/2));
                break;
            case CENTER:
                M.runAction(new TurnToAngle(-90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(10, -90, 2*Math.PI));
                M.runAction(new TurnToAngle(-120));
                M.runAction(new DriveToDistance(8, -120, 2*Math.PI));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-6, -120, Math.PI/2));
                break;
            case RIGHT:
                M.runAction(new TurnToAngle(-90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(4, -90));
                M.runAction(new TurnToAngle(-125));
                M.runAction(new DriveToDistance(14, -125));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, -125, Math.PI));
                break;
        }
    }
}
