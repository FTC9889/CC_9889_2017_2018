package com.team9889.test.Autonomus.Control;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DrivePath;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnLeftMotor;
import com.team9889.auto.actions.Drive.TurnRightMotor;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Glyph.GlyphDeployOverTheBack;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Intake.IntakeDeployWideIntake;
import com.team9889.auto.actions.Jewel.JewelHitColor;
import com.team9889.test.Autonomus.Control.paths.PathContainer;
import com.team9889.test.Autonomus.Control.paths.RedFrontCryptoboxFront;

/**
 * Created by joshua9889 on 2/20/2018.
 */

@Autonomous
@Disabled
public class TestDriveByPoint extends AutoModeBase {
    PathContainer path = new RedFrontCryptoboxFront();
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(true);
AutoModeBase M = this;
        M.runAction(new JewelHitColor(JewelColor.Red, this));
        M.runAction(new DriveToDistance(20, 0, Math.PI));
        M.runAction(new TurnToAngle(67));
        M.runAction(new DriveToDistance(-32, 67));
        M.runAction(new TurnRightMotor(0));
        M.runAction(new DriveToDistance(-7, 0));
        M.Robot.getDrive().setLeftRightPower(0.2, 0.2);
        while (M.Robot.getDrive().getPingDistance()<6 && M.opModeIsActive())
            idle();
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.runAction(new GlyphDeployOverTheBack());
        M.runAction(new GlyphRelease());
        M.runAction(new DriveToDistance(3, 0, 2*Math.PI));
        M.sleep(200);
        M.runAction(new GlyphDeployToIntake());
        M.runAction(new DriveToDistance(25, 5, 3*Math.PI));
        M.sleep(1000);
        M.Robot.getLift().setServoPosition(0.4);
        M.Robot.getLift().clamp();
        M.Robot.getIntake().stopIntake();
        M.sleep(200);
        M.ThreadAction(new GlyphDeployOverTheBack());
        M.runAction(new DriveToDistance(-24, -5, 5*Math.PI));
        while(M.t.seconds()<28 && M.opModeIsActive()) {
            M.runAction(new GlyphRelease());
            idle();
        }
        M.runAction(new DriveToDistance(3,0, 7*Math.PI));

//        M.runAction(new GlyphStorePreload());
//        M.runAction(new IntakeDeployAndCollect());
//        M.runAction(new TurnToAngle(-45));
//        M.runAction(new DriveToDistance(12, -45, 5*Math.PI));
//        M.sleep(1000);
//
//        M.runAction(new DriveToDistance(-10, -45, 2*Math.PI));
//        M.Robot.getLift().setServoPosition(0.4);
//        M.Robot.getLift().clamp();
//        M.Robot.getIntake().stopIntake();
//        M.sleep(500);
//        int angle = -139;
//        M.runAction(new TurnToAngle(angle));
//        M.runAction(new GlyphDeployToFirstLevelTwoGlyph());
//        M.runAction(new DriveToDistance(24, angle, Math.PI));
//        M.runAction(new DriveTimeAction(1000, angle, Math.PI));
//        M.runAction(new GlyphRelease());
//        M.runAction(new DriveToDistance(-5, angle, Math.PI));
//        M.runAction(new DriveTimeAction(500, 2*Math.PI, angle));
//        M.runAction(new DriveToDistance(-5, angle, Math.PI));
    }
}
