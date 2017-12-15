package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveTimeAction;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.GlyphRetractArm;
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
        // Drive off platform
        M.runAction(new DriveToDistance(17, 0, 0.1));
        M.sleep(500);

        // Turn left to cyr4tobocs tstdfs
        M.runAction(new TurnToAngle(90, 0.6));
        M.sleep(200);

        // Drive foward 25
        M.runAction(new DriveToDistance(25, 90, 0.4));

        // Deploy Arm
        M.runAction(new GlyphDeployToFirstLevel());

        // Used for turning to correct column
        switch (column){
            case RIGHT:
                M.runAction(new TurnToAngle(100, 0.4));
                M.sleep(100);

                M.runAction(new DriveToDistance(4, 100, 0.4));
                M.sleep(500);
                break;
            case CENTER:
                M.runAction(new TurnToAngle(110, 0.6));
                M.sleep(100);

                M.runAction(new DriveToDistance(7, 110, 0.2));
                M.sleep(400);

                break;
            case LEFT:
                M.runAction(new TurnToAngle(120, 0.6));
                M.sleep(100);

                M.runAction(new DriveToDistance(14, 120, 0.2));
                M.sleep(400);

                break;
        }

        M.runAction(new GlyphRelease());

        M.runAction(new DriveTimeAction(500, -1));
        M.sleep(100);

        M.runAction(new GlyphRetractArm());

        M.runAction(new DriveTimeAction(750, 0.4));
        M.sleep(100);

        M.runAction(new DriveTimeAction(300, -0.1));
    }
}
