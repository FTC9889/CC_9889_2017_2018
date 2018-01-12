package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveTimeAction;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.GlyphRetractArm;
import com.team9889.auto.actions.JewelExtend;
import com.team9889.auto.actions.TurnToAngle;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static android.R.attr.left;
import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 11/24/2017.
 * We need to fix this...
 */

public class BLUE_FOWARD {

    public BLUE_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        // Determine what column to score the glyph in
        switch (column){
            case RIGHT:
                M.runAction(new DriveToDistance(18, 0));
                M.runAction(new TurnToAngle(95));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(28, 95));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, 95, Math.PI));
                break;
            case CENTER:
                M.runAction(new DriveToDistance(18, 0));
                M.runAction(new TurnToAngle(90));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(12, 90));
                M.runAction(new TurnToAngle(110));
                M.runAction(new DriveToDistance(15, 110));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-4, 110));
                break;
            case LEFT:
                M.runAction(new DriveToDistance(18, 0));
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
