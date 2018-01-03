package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.GlyphRetractArm;
import com.team9889.auto.actions.GlyphStorePreload;
import com.team9889.auto.actions.IntakeDeployAndCollect;
import com.team9889.auto.actions.IntakePull;
import com.team9889.auto.actions.IntakeSwivel;
import com.team9889.auto.actions.JewelExtend;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.auto.actions.TurnLeftMotor;
import com.team9889.auto.actions.TurnRightMotor;
import com.team9889.auto.actions.TurnToAngle;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 1/2/2018.
 */

@Autonomous
public class TestBlueBack extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

        // Hit Jewel off
        jewel_Color = JewelColor.Red;
        runAction(new JewelHitColor(JewelColor.Red));

        runAction(new DriveToDistance(17, 0));
        runAction(new TurnToAngle(120));

        ThreadAction(new GlyphDeployToFirstLevel());
        runAction(new DriveToDistance(26, 120, PI));

        runAction(new TurnRightMotor(177));
        runAction(new DriveToDistance(12, 177, 3*PI/2));

        runAction(new GlyphRelease());

        runAction(new DriveToDistance(-7, 178));

        runAction(new GlyphRetractArm());
        runAction(new TurnToAngle(0));

        ThreadAction(new IntakeDeployAndCollect());
        runAction(new DriveToDistance(10, 0, 4*PI));
        runAction(new JewelExtend());


        // Drive off balance stone
//        runAction(new DriveToDistance(24, 0, 3*PI));
//        runAction(new GlyphStorePreload());
//        runAction(new IntakeDeployAndCollect());
//        runAction(new TurnToAngle(60));
//
//        runAction(new DriveToDistance(10, 60, 6*PI));
//        sleep(250);
//        runAction(new IntakePull());
//        sleep(250);
//        runAction(new IntakeSwivel());
//        runAction(new IntakePull());
//
//        runAction(new DriveToDistance(-10, 60));
//
//        runAction(new TurnToAngle(125));
//        ThreadAction(new GlyphDeployToFirstLevel());
//        runAction(new DriveToDistance(24, 125));
//        runAction(new TurnRightMotor(180));
//        runAction(new DriveToDistance(10, 180, PI));
//        runAction(new GlyphRelease());
//        runAction(new DriveToDistance(-4, 180, PI/2));


    }
}
