package com.team9889.auto.modes.MultiGlyph.TwoGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphRetractArm;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Intake.IntakeDeployWideIntake;
import com.team9889.auto.actions.Intake.IntakePull;
import com.team9889.auto.actions.Intake.IntakeSwivel;
import com.team9889.auto.actions.Drive.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class RED_FOWARD_TWO_GLYPH {
    public RED_FOWARD_TWO_GLYPH(AutoModeBase M, RelicRecoveryVuMark column){

        // Drive Off Balance Stone
        M.runAction(new DriveToDistance(18, 0, Math.PI));

        M.runAction(new GlyphStorePreload());

        M.runAction(new IntakeDeployWideIntake());

        M.runAction(new TurnToAngle(45));

        M.runAction(new DriveToDistance(12, 45, 5*Math.PI));
        M.sleep(1000);
        M.runAction(new IntakeDeployAndCollect());

        M.runAction(new DriveToDistance(-13, 45, 2*Math.PI));

        M.Robot.getLift().setServoPosition(0.4);
        M.Robot.getLift().clamp();
        M.Robot.getIntake().stopIntake();
        M.sleep(500);

        switch (column){
            case LEFT:
                M.runAction(new TurnToAngle(-92));
                M.runAction(new GlyphDeployToFirstLevelTwoGlyph());
                M.runAction(new DriveTimeAction(2000, 3*Math.PI, -92));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -92, Math.PI));
                M.runAction(new DriveTimeAction(500, 2*Math.PI, -92));
                M.runAction(new DriveToDistance(-5, -92, Math.PI));
                break;
            case CENTER:
                M.runAction(new TurnToAngle(-105));
                M.runAction(new GlyphDeployToFirstLevelTwoGlyph());
                M.runAction(new DriveTimeAction(3500, Math.PI, -105));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -105, Math.PI));
                M.runAction(new DriveTimeAction(500, 2*Math.PI, -105));
                M.runAction(new DriveToDistance(-5, -105, Math.PI));
                break;
            case RIGHT:
                M.runAction(new TurnToAngle(-112));
                M.runAction(new GlyphDeployToFirstLevelTwoGlyph());
                M.runAction(new DriveTimeAction(3500, Math.PI, -112));
                M.runAction(new GlyphRelease());
                M.runAction(new DriveToDistance(-5, -112, Math.PI));
                M.runAction(new DriveTimeAction(500, 2*Math.PI, -112));
                M.runAction(new DriveToDistance(-5, -112, Math.PI));
                break;
        }

    }
}
