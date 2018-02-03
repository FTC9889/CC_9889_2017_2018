package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_BACK {
    public BLUE_BACK(AutoModeBase M, RelicRecoveryVuMark column) {
        M.runAction(new DriveToDistance(19, 0, Math.PI));
        M.runAction(new TurnToAngle(-67));
        M.ThreadAction(new GlyphDeployToFirstLevel());

        // Determine what column to score the glyph in
        switch (column){
            case LEFT:
                M.runAction(new DriveToDistance(-29, -67));
                M.runAction(new TurnToAngle(-90));
                M.runAction(new TurnToAngle(-160));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(17, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180, Math.PI));
                M.runAction(new DriveToDistance(5, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
            case CENTER:
                M.runAction(new DriveToDistance(-33, -67));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(13, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180));
                M.runAction(new DriveToDistance(4, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
            case RIGHT:
                M.runAction(new DriveToDistance(-41, -67));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(8, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180));
                M.runAction(new DriveToDistance(6, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
        }
    }
}
