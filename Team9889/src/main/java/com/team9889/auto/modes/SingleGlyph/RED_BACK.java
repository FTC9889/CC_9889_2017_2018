package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 4/10/2017.
 */

public class RED_BACK{

    public RED_BACK(AutoModeBase M, RelicRecoveryVuMark column){
        M.runAction(new DriveToDistance(18, 0));
        M.runAction(new TurnToAngle(65));
        M.ThreadAction(new GlyphDeployToFirstLevel());

        switch (column){
            case LEFT:
                M.runAction(new DriveToDistance(-45, 65, 2*Math.PI));
                M.runAction(new TurnToAngle(180));
                M.runAction(new DriveToDistance(8, 180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, 180));
                M.runAction(new DriveToDistance(5, 180));
                M.runAction(new DriveToDistance(-3, 180));
                break;
            case CENTER:
                M.runAction(new DriveToDistance(-38, 65, Math.PI));
                M.runAction(new TurnToAngle(180));
                M.runAction(new DriveToDistance(10, 180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, 180));
                M.runAction(new DriveToDistance(5, 180));
                M.runAction(new DriveToDistance(-3, 180));
                break;
            case RIGHT:
                M.runAction(new DriveToDistance(-29, 65, 2*Math.PI));
                M.runAction(new TurnToAngle(180));
                M.runAction(new DriveToDistance(12, 180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, 180));
                M.runAction(new DriveToDistance(5, 180));
                M.runAction(new DriveToDistance(-3, 180));
                break;
        }
    }
}
