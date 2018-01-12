package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveTimeAction;
import com.team9889.auto.actions.DriveToDistance;
import com.team9889.auto.actions.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.GlyphRelease;
import com.team9889.auto.actions.GlyphStorePreload;
import com.team9889.auto.actions.IntakeDeployAndCollect;
import com.team9889.auto.actions.IntakeDeployWideIntake;
import com.team9889.auto.actions.IntakeOuttake;
import com.team9889.auto.actions.IntakePull;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.auto.actions.TurnToAngle;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static java.lang.Math.PI;

/**
 * Created by joshua9889 on 1/2/2018.
 */

@Autonomous
@Disabled
public class TestNewAuto extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true);

        // Hit Jewel off
        runAction(new JewelHitColor(JewelColor.Red));

        // Drive off balance stone
        runAction(new DriveToDistance(17, 0));
        runAction(new TurnToAngle(-90));
        ThreadAction(new GlyphDeployToFirstLevel());
        runAction(new DriveToDistance(20, -91));
        runAction(new TurnToAngle(-110));
        runAction(new DriveToDistance(12, -110));
        runAction(new GlyphRelease());
        runAction(new DriveToDistance(-6, -91));
        runAction(new DriveTimeAction(1000, 4*PI, -91));
        runAction(new DriveToDistance(-4, -91));


//        runAction(new GlyphStorePreload());
//        ThreadAction(new IntakeDeployWideIntake());
//
//        RobotLog.a("Started: " + String.valueOf(getRuntime()));
//        runAction(new TurnToAngle(60));
//        RobotLog.a("Finished: " + String.valueOf(getRuntime()));
//
//        runAction(new DriveToDistance(14, 60, 4*PI));
//
//         Wait a little
//        sleep(400);
//
//        runAction(new DriveToDistance(-14, 60));

//        RelicRecoveryVuMark currentVumark = RelicRecoveryVuMark.LEFT;

//        switch (currentVumark){
//            case CENTER:
//                runAction(new TurnToAngle(-97));
//                runAction(new DriveToDistance(26, -97, 3*PI/2));
//
//                runAction(new IntakeOuttake());
//
//                runAction(new DriveToDistance(-5, -97));
//                runAction(new GlyphDeployToFirstLevel());
//                runAction(new DriveTimeAction(1000, Math.PI, -97));
//                break;
//            case LEFT:
//                runAction(new TurnToAngle(-90));
//
//                runAction(new DriveToDistance(27, -95));
//
//                runAction(new GlyphRelease());
//                runAction(new DriveToDistance(-4, -90));
//                break;
//        }
    }
}
