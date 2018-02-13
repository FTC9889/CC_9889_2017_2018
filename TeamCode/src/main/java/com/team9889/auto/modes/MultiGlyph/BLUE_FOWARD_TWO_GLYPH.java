package com.team9889.auto.modes.MultiGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 1/2/2018.
 */

public class BLUE_FOWARD_TWO_GLYPH {
    public BLUE_FOWARD_TWO_GLYPH(AutoModeBase M, RelicRecoveryVuMark column){
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
    }
}