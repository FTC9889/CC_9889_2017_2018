package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Glyph.GlyphDeployOverTheBack;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Intake.IntakeDeployWideIntake;
import com.team9889.auto.actions.Intake.IntakePull;
import com.team9889.auto.actions.Jewel.JewelHitColor;

/**
 * Created by joshua9889 on 2/17/2018.
 */

@Autonomous
public class droponeCollect extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(true);

        AutoModeBase M = this;

        M.runAction(new JewelHitColor(JewelColor.Red, this));
        M.runAction(new DriveToDistance(20, 0, Math.PI));

        M.runAction(new TurnToAngle(-91));
        M.ThreadAction(new GlyphDeployToFirstLevel());
        M.runAction(new DriveToDistance(20, -91));

        M.runAction(new GlyphRelease());

        M.runAction(new DriveToDistance(-5, -92, Math.PI/2));
        M.runAction(new TurnToAngle(65));
        M.ThreadAction(new GlyphDeployToIntake());
        M.ThreadAction(new IntakeDeployWideIntake());
        M.runAction(new DriveToDistance(27, 65, 5*Math.PI));
        M.sleep(500);
        M.runAction(new IntakeDeployAndCollect());
        M.runAction(new DriveToDistance(-5, 65));
        M.runAction(new DriveToDistance(9, 65));
        M.sleep(500);
        M.ThreadAction(new IntakePull());
        M.runAction(new DriveToDistance(-10, 70));
        M.runAction(new TurnToAngle(70));
        M.ThreadAction(new GlyphDeployOverTheBack());
        M.runAction(new DriveToDistance(-25, 70));
        M.runAction(new GlyphRelease());

    }
}
