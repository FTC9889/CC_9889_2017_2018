package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.JewelExtend;
import com.team9889.auto.actions.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class RED_FOWARD {

    public RED_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        M.runAction(new DriveToDistance(18, 0));

        // Determine what column to score the glyph in
        switch (column){
            case LEFT:
                M.runAction(new TurnToAngle(-92));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(27, -92));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, -92, Math.PI/2));
                break;
            case CENTER:
                M.runAction(new TurnToAngle(-90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(12, -90));
                M.runAction(new TurnToAngle(-110));
                M.runAction(new DriveToDistance(11, -110));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, -110));
                M.runAction(new TurnToAngle(60));
                M.runAction(new DriveToDistance(5, 60));
                M.runAction(new JewelExtend());
                break;
            case RIGHT:
                M.runAction(new TurnToAngle(-90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(10, -90));
                M.runAction(new TurnToAngle(-122));
                M.runAction(new DriveToDistance(22, -122));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, -122, Math.PI));
                M.runAction(new TurnToAngle(60));
                M.runAction(new DriveToDistance(13, 60));
                M.runAction(new JewelExtend());
                break;
        }
    }
}
