package com.team9889.auto.modes.MultiGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveTimeAction;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.GlyphRetractArm;
import com.team9889.auto.actions.IntakeDeployAndCollect;
import com.team9889.auto.actions.IntakePull;
import com.team9889.auto.actions.IntakeSwivel;
import com.team9889.auto.actions.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class RED_FOWARD_TWO_GLYPH {
    public RED_FOWARD_TWO_GLYPH(AutoModeBase M, RelicRecoveryVuMark column){
        // Drive off balance stone
        M.runAction(new DriveToDistance(21, 0));

        // Turn to the cryptobox
        M.runAction(new TurnToAngle(-90));

        // Deploy Lift
        M.ThreadAction(new GlyphDeployToFirstLevel());

        // Drive to cryptobox
        M.runAction(new DriveToDistance(30, -91));

        // Release Glyph
        M.runAction(new GlyphRelease());

        // Back away from cryptobox
        M.runAction(new DriveToDistance(-10, -90));

        // Retract Lift and Deploy Intake
        M.ThreadAction(new GlyphRetractArm());
        M.ThreadAction(new IntakeDeployAndCollect());

        // Turn to Glyph Pit
        M.runAction(new TurnToAngle(60));

        // Drive into Glyph Pit
        M.runAction(new DriveToDistance(28, 60));

        // Wait a little
        M.sleep(400);

        // Pull any glyphs in
        M.runAction(new IntakePull());
        M.sleep(250);

        // Straighten Glyph out
        M.runAction(new IntakeSwivel());

        // Deploy Intake
        M.runAction(new IntakeDeployAndCollect());

        // Drive a little farther forward
        M.runAction(new DriveToDistance(4, 60));

        // Pull any glyphs in
        M.runAction(new IntakePull());
        M.sleep(100);

        // Back away from Glyph Pit
        M.runAction(new DriveToDistance(-26, 60));

        // Straighten Glyphs out
        M.ThreadAction(new IntakeSwivel());

        // Turn to Cryptobox
        M.runAction(new TurnToAngle(-115));

        // Deploy Lift
        M.ThreadAction(new GlyphDeployToFirstLevel());
        M.sleep(100);

        // Drive to Crytobox
        M.runAction(new DriveTimeAction(900, 5*Math.PI, -115));

        // Release Any glyphs into Cryptobox
        M.runAction(new GlyphRelease());

        // Back up, then ram, and then back up.
        M.runAction(new DriveToDistance(-2, -115));
        M.runAction(new DriveTimeAction(300, 4*Math.PI, -115));
        M.runAction(new DriveToDistance(-3, -115));
    }
}
