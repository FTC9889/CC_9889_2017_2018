package com.team9889.auto.modes.MultiGlyph.TwoGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 2/20/2018.
 */

public class RED_BACK_TWO_GLYPH {

    public RED_BACK_TWO_GLYPH(AutoModeBase M, RelicRecoveryVuMark column) {
        M.runAction(new DriveToDistance(18, 0, Math.PI));
        M.runAction(new GlyphStorePreload());
        M.runAction(new IntakeDeployAndCollect());
        M.runAction(new TurnToAngle(-45));
        M.runAction(new DriveToDistance(12, -45, 5*Math.PI));
        M.sleep(1000);

        M.runAction(new DriveToDistance(-10, -45, 2*Math.PI));
        M.Robot.getLift().setServoPosition(0.4);
        M.Robot.getLift().clamp();
        M.Robot.getIntake().stopIntake();
        M.sleep(500);

        switch (column){
            case CENTER:
                int angle = -145;
                M.runAction(new TurnToAngle(angle));
                M.runAction(new GlyphDeployToFirstLevelTwoGlyph());
                M.runAction(new DriveTimeAction(2000, 2*Math.PI, angle));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, angle, Math.PI));
                M.runAction(new DriveTimeAction(500, 2*Math.PI, angle));
                M.runAction(new DriveToDistance(-5, angle, Math.PI));
                break;
        }

    }
}