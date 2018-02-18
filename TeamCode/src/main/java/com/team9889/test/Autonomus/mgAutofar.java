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
import com.team9889.auto.actions.Intake.IntakePull;
import com.team9889.auto.actions.Intake.IntakeSwivel;
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
        runAction(new DriveToDistance(20, 0, Math.PI));
        runAction(new GlyphStorePreload());
        ThreadAction(new IntakeDeployWideIntake());
        runAction(new TurnToAngle(60));
        runAction(new DriveToDistance(15, 60, 4*Math.PI));
        runAction(new IntakeDeployAndCollect());
        sleep(500);
        ThreadAction(new IntakePull());
        runAction(new DriveToDistance(-15, 60));
        Robot.getLift().clamp();
        runAction(new TurnToAngle(70));


        Robot.getDrive().setLeftRightPower(-2*Math.PI, -2*Math.PI);
        while (Robot.getDrive().getPingDistance()>10 && opModeIsActive())
            idle();
        Robot.getDrive().setLeftRightPower(0,0);
        sleep(100);

        Robot.getDrive().setVelocityTarget(Math.PI/2, Math.PI/2);
        while (Robot.getDrive().getPingDistance()<5 &&opModeIsActive())
            idle();
        Robot.getDrive().setLeftRightPower(0,0);

        runAction(new GlyphDeployOverTheBack());
        sleep(2000);
        runAction(new GlyphRelease());
        runAction(new DriveToDistance(5, 70, Math.PI));

        ThreadAction(new GlyphDeployToIntake());
        runAction(new DriveToDistance(25, 70, 5*Math.PI));
        ThreadAction(new GlyphDeployOverTheBack());
        runAction(new DriveToDistance(-25, 70, 6*Math.PI));
        runAction(new GlyphRelease());

    }
}