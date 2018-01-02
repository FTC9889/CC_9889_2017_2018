package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;
import com.team9889.subsystems.Drive;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_BACK {
    public BLUE_BACK(AutoModeBase M, RelicRecoveryVuMark column) {
        // Drive off platform
        M.runAction(new DriveToDistance(20, 0));

        // Turn 90
        M.runAction(new TurnToAngle(90));
		
		// Drive Straight 10"
        M.runAction(new DriveToDistance(10, 90));
        M.sleep(250);
		
		// Turn to 135 degrees
        M.runAction(new TurnToAngle(135));

		// Drive Straight 30" to cryptobox
        M.runAction(new DriveToDistance(10, 135));
        M.sleep(100);

		// Turn to face cryptobox
        M.runAction(new TurnToAngle(-180));

        // Deploy Glyph Lift
        M.runAction(new GlyphDeployToFirstLevel());

		// Drive foward to depost glyph in box
        M.runAction(new DriveToDistance(10, -180));
		
		// Release glyph
        M.runAction(new GlyphRelease());
		
		// Back away
        M.runAction(new DriveTimeAction(500, -0.3));
		
		// Pull everything in
        M.runAction(new GlyphRetractArm());
		
		// Push glyph all the way in
        M.runAction(new DriveTimeAction(750, 0.2));
        M.sleep(100);

		// Back away from glyph
        M.runAction(new DriveTimeAction(300, -0.1));
    }
}
