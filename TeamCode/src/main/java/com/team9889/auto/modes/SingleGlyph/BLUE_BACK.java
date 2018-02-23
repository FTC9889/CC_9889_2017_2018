package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnLeftMotor;
import com.team9889.auto.actions.Drive.TurnRightMotor;
import com.team9889.auto.actions.Glyph.GlyphDeployOverTheBack;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_BACK {
    public BLUE_BACK(AutoModeBase M, RelicRecoveryVuMark column) {
        M.runAction(new DriveToDistance(20, 0, Math.PI));
        M.runAction(new TurnToAngle(-65));
        M.ThreadAction(new GlyphDeployToFirstLevel());

        // Determine what column to score the glyph in
        switch (column){
            case LEFT:
                M.runAction(new DriveToDistance(-28, -65, Math.PI));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(13, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180));
                M.runAction(new DriveToDistance(4, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
            case CENTER:
                M.runAction(new DriveToDistance(-33, -65));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(13, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180));
                M.runAction(new DriveToDistance(4, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
            case RIGHT:
                M.runAction(new DriveToDistance(-45, -65));
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
