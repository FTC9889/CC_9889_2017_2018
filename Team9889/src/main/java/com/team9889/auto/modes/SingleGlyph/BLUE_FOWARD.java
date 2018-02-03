package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 11/24/2017.
 * We need to fix this...
 */

public class BLUE_FOWARD {

    public BLUE_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        M.runAction(new DriveToDistance(19, 0, Math.PI));

        // Determine what column to score the glyph in
        switch (column){
            case RIGHT:
                M.runAction(new TurnToAngle(95));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(28, 95));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, 95, Math.PI));
                break;
            case CENTER:
                M.runAction(new TurnToAngle(90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(12, 90));
                M.runAction(new TurnToAngle(110));
                M.runAction(new DriveToDistance(15, 110));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, 110));
                M.runAction(new DriveToDistance(4, 110));
                M.runAction(new DriveToDistance(-3, 110));
                break;
            case LEFT:
                M.runAction(new TurnToAngle(116));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(35, 116, Math.PI));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, 116, Math.PI));
                M.runAction(new DriveToDistance(5, 116));
                M.runAction(new DriveToDistance(-3, 116));
                break;
        }
    }
}
