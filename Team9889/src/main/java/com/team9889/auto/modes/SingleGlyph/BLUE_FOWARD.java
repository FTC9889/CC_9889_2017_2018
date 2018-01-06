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
 */

public class BLUE_FOWARD {

    public BLUE_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        // Determine what column to score the glpyh in
        switch (column){
            case LEFT:
                M.runAction(new DriveToDistance(19, 0));
                M.runAction(new TurnToAngle(-67));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(-28, -67));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(17, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180, Math.PI));
                M.runAction(new DriveToDistance(5, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
            case CENTER:
                M.runAction(new DriveToDistance(19, 0));
                M.runAction(new TurnToAngle(-67));
                M.ThreadAction(new GlyphDeployToFirstLevel());
                M.runAction(new DriveToDistance(-33, -67));
                M.runAction(new TurnToAngle(-180));
                M.runAction(new DriveToDistance(13, -180));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -180));
                M.runAction(new DriveToDistance(4, -180));
                M.runAction(new DriveToDistance(-3, -180));
                break;
            case RIGHT:
                M.runAction(new DriveToDistance(19, 0));
                M.runAction(new TurnToAngle(-67));
                M.ThreadAction(new GlyphDeployToFirstLevel());
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
