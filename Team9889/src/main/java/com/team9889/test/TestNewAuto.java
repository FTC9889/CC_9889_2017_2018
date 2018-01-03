package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.GlyphRetractArm;
import com.team9889.auto.actions.IntakeDeployAndCollect;
import com.team9889.auto.actions.IntakeDeployWideIntake;
import com.team9889.auto.actions.IntakePull;
import com.team9889.auto.actions.IntakeSwivel;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.auto.actions.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 1/2/2018.
 */

@Autonomous
public class TestNewAuto extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

        // Hit Jewel off
        jewel_Color = JewelColor.Red;
        runAction(new JewelHitColor(JewelColor.Red));

        // Drive off balance stone
        runAction(new DriveToDistance(24, 0, 2*PI));

        Robot.getIntake().clearArm();
        sleep(300);
        Robot.getLift().release();
        sleep(400);

        runAction(new IntakeSwivel());
        runAction(new GlyphRetractArm());
        ThreadAction(new IntakeDeployWideIntake());

        runAction(new TurnToAngle(60));

        runAction(new DriveToDistance(14, 60, 4*PI));

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
        runAction(new DriveToDistance(4, 60, 6* PI));

        // Pull any glyphs in
        runAction(new IntakePull());
        sleep(100);

        runAction(new DriveToDistance(-19, 60));

        RelicRecoveryVuMark currentVumark = RelicRecoveryVuMark.LEFT;

        switch (currentVumark){
            case CENTER:
                runAction(new TurnToAngle(-97));
                ThreadAction(new GlyphDeployToFirstLevel());

                sleep(400);

                runAction(new DriveToDistance(30, -97, 3*PI/2));
                break;
            case LEFT:
                runAction(new TurnToAngle(-91));
                ThreadAction(new GlyphDeployToFirstLevel());

                sleep(400);

                runAction(new DriveToDistance(30, -90, 2*PI));
                break;
        }

        runAction(new GlyphRelease());
        runAction(new DriveToDistance(-7, -97, PI/2));


    }
}
