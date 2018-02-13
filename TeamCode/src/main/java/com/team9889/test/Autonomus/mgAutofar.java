package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnLeftMotor;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Glyph.GlyphDeployOverTheBack;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphRetractArm;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Intake.IntakeDeployWideIntake;
import com.team9889.auto.actions.Jewel.JewelHitColor;

/**
 * Created by joshua9889 on 2/6/2018.
 */

@Autonomous
public class mgAutofar extends AutoModeBase {
    public static LinearOpMode mg;

    @Override
    public void runOpMode() throws InterruptedException {
        mg = this;
        waitForStart(true);

//        runAction(new JewelHitColor(JewelColor.Red, this));
        runAction(new DriveToDistance(18, 0));
        runAction(new GlyphStorePreload());
        ThreadAction(new IntakeDeployAndCollect());
        runAction(new TurnToAngle(75));
        runAction(new DriveToDistance(15, 75, 4*Math.PI));
        sleep(1000);
        runAction(new DriveToDistance(-33, 75));
        runAction(new TurnLeftMotor(90));

        Robot.getDrive().setLeftRightPower(-0.2, -0.2);
        while (Robot.getDrive().getPingDistance()>10)
            idle();
        Robot.getDrive().setLeftRightPower(0,0);

        Robot.getDrive().setLeftRightPower(0.1, -0.1);
        while (Robot.getDrive().getPingDistance()<10)
            idle();
        Robot.getDrive().setLeftRightPower(0,0);

        runAction(new GlyphDeployOverTheBack());
        sleep(2000);
        runAction(new GlyphRelease());
        runAction(new DriveToDistance(5, 90, Math.PI));

        runAction(new GlyphDeployToIntake());
        sleep(2000);

    }
}