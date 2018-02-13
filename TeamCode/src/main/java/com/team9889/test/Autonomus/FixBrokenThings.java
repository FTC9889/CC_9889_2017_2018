package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Jewel.JewelHitColor;
import com.team9889.auto.actions.Drive.TurnToAngle;

/**
 * Created by joshua9889 on 12/30/2017.
 * OpMode to test stuff
 */

@Autonomous
@Disabled
public class FixBrokenThings extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(true);

        AutoModeBase M = this;
        // Hit Jewel off
        runAction(new JewelHitColor(JewelColor.Blue, this));
        // Drive off balance stone
        M.runAction(new DriveToDistance(18, 0));

        M.runAction(new GlyphStorePreload());

        M.runAction(new IntakeDeployAndCollect());

        M.runAction(new TurnToAngle(-45));

        M.runAction(new DriveToDistance(12, -45, 5*Math.PI));
        M.sleep(1000);

        M.Robot.getLift().setServoPosition(0.4);
        M.Robot.getLift().clamp();
        M.Robot.getIntake().stopIntake();
        M.sleep(400);

        M.runAction(new DriveToDistance(-12, -45));

        M.runAction(new TurnToAngle(95));
        M.ThreadAction(new GlyphDeployToFirstLevelTwoGlyph());
        M.runAction(new DriveTimeAction(2000, 2*Math.PI, 95));

        M.runAction(new GlyphRelease());
        M.runAction(new DriveToDistance(-5, 95, Math.PI));
        M.runAction(new DriveToDistance(4, 95));
        M.runAction(new DriveToDistance(-3, 95));
    }
}
