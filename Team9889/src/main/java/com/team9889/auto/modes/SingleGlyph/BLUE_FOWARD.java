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
        M.runAction(new DriveToDistance(17, 0.0, 0.1));
        M.sleep(200);

        // Turn to cryptobox
        M.runAction(new TurnToAngle(90, 0.6));
        M.sleep(200);

        //Drive 25" to cryptobox
        M.runAction(new DriveToDistance(23, 90, 0.4));

        // Deploy arm w/glyph inside
        M.runAction(new GlyphDeployToFirstLevel());

        // Determine what column to score the glpyh in
        switch (column){
            case LEFT:
                // Turn to Left column
                M.runAction(new TurnToAngle(105, 0.4));

                // Drive foward to place glpyh
                M.runAction(new DriveToDistance(2, 90, 0.4));
                M.sleep(500);
                break;
            case CENTER:
                // Turn to Center column
                M.runAction(new TurnToAngle(125, 0.4));
                M.sleep(100);

                // Drive foward to place glyph
                M.runAction(new DriveToDistance(10, 125, 0.4));
                M.sleep(400);

                break;
            case RIGHT:
                // Turn to Right column
                M.runAction(new TurnToAngle(145, 0.4));
                M.sleep(100);

                // Drive foward to place glyph
                M.runAction(new DriveToDistance(14, 145, 0.2));
                M.sleep(400);
                M.runAction(new TurnToAngle(140, 0.6));
                M.sleep(200);

                break;
        }

        // Release glpyh
        M.runAction(new GlyphRelease());

        // Backup
        M.runAction(new DriveTimeAction(500, -1));
        M.sleep(100);

        // Retract everything
        M.runAction(new GlyphRetractArm());

        // RAM INTO THINGS!!
        M.runAction(new DriveTimeAction(750, 0.4));
        M.sleep(100);
        M.runAction(new DriveTimeAction(300, -0.1));
    }
}
