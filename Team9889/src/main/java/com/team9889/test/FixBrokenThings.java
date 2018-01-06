package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

import static java.lang.Math.*;

/**
 * Created by joshua9889 on 12/30/2017.
 * OpMode to test stuff
 */

@Autonomous
@Disabled
public class FixBrokenThings extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

        // Hit Jewel off
        jewel_Color = JewelColor.Red;
        runAction(new JewelHitColor(JewelColor.Red));

        // Drive off balance stone
        runAction(new DriveToDistance(21, 0));

        // Turn to the cryptobox
        runAction(new TurnToAngle(-90));

        // Deploy Lift
        ThreadAction(new GlyphDeployToFirstLevel());

        // Drive to cryptobox
        runAction(new DriveToDistance(30, -92));

        // Release Glyph
        runAction(new GlyphRelease());
        runAction(new DriveToDistance(-3, -90));

        // Back away from cryptobox
        runAction(new DriveToDistance(-7, -90));

        // Retract Lift and Deploy Intake
        ThreadAction(new GlyphRetractArm());
        ThreadAction(new GlyphRetractArm());

        // Turn to Glyph Pit
        runAction(new TurnToAngle(60));

        ThreadAction(new IntakeDeployWideIntake());

        // Drive into Glyph Pit
        runAction(new DriveToDistance(28, 60));

        // Wait a little
        sleep(400);

        // Pull any glyphs in
        runAction(new IntakePull());
        sleep(500);

        // Straighten Glyph out
        runAction(new IntakeSwivel());

        // Deploy Intake
        runAction(new IntakeDeployAndCollect());

        // Drive a little farther forward
        runAction(new DriveToDistance(4, 60));

        // Pull any glyphs in
        runAction(new IntakePull());
        sleep(100);

        // Back away from Glyph Pit
        runAction(new DriveToDistance(-26, 60));

        // Straighten Glyphs out
        ThreadAction(new IntakeSwivel());

        // Turn to Cryptobox
        runAction(new TurnToAngle(-115));

        // Deploy Lift
        ThreadAction(new GlyphDeployToFirstLevel());

        sleep(100);
        runAction(new TurnToAngle(-115));

        // Drive to Crytobox
        runAction(new DriveTimeAction(800, 4* PI, -115));

        // Release Any glyphs into Cryptobox
        runAction(new GlyphRelease());

        // Back up, then ram, and then back up.
        runAction(new DriveToDistance(-2, -115));
        runAction(new DriveTimeAction(300, 4 * PI, -115));
        runAction(new DriveToDistance(-3, -115));

    }
}
