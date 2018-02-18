package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnRightMotor;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Glyph.GlyphDeployOverTheBack;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Jewel.JewelExtend;
import com.team9889.auto.actions.Jewel.JewelHitColor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 2/12/2018.
 */

@Autonomous
public class TunePID extends AutoModeBase{
    @Override
    public void runOpMode() {
        waitForStart(true);
        AutoModeBase M = this;
        M.runAction(new JewelHitColor(JewelColor.Blue, this));
        M.runAction(new DriveToDistance(20, 0, Math.PI/2));
        M.runAction(new TurnToAngle(45));
        M.runAction(new GlyphStorePreload());
        M.runAction(new GlyphDeployToIntake());
        M.runAction(new DriveToDistance(15, 45, 5*Math.PI));
        sleep(1000);
        M.Robot.getLift().clamp();
        M.Robot.getIntake().clearArm();
        M.runAction(new DriveToDistance(-14, 45));
        M.runAction(new GlyphDeployOverTheBack());
        M.runAction(new TurnToAngle(-67));
        M.runAction(new DriveToDistance(-20, -67));
        M.runAction(new TurnToAngle(0));
        M.runAction(new DriveTimeAction(200, -0.3));
        M.runAction(new GlyphRelease());
        M.runAction(new DriveToDistance(5, 0));

        //runAction(new TurnToAngle(90));
//        runAction(new JewelHitColor(JewelColor.Blue, this));
//        M.runAction(new DriveToDistance(19, 0, Math.PI));
//        M.runAction(new TurnToAngle(-67));
//        ThreadAction(new GlyphDeployOverTheBack());
//        M.runAction(new DriveToDistance(-29, -67));
//        M.runAction(new TurnToAngle(0));
//        Robot.getDrive().setLeftRightPower(-0.5, -0.5);
//        while(Robot.getDrive().getPingDistance()>6){
//            RobotLog.a("Ping: "+String.valueOf(Robot.getDrive().getPingDistance()));
//            idle();
//        }
//        Robot.getDrive().setLeftRightPower(0,0);
//        runAction(new GlyphRelease());
//        runAction(new DriveToDistance(4, 0, Math.PI));
//        runAction(new GlyphDeployToIntake());
//        runAction(new DriveToDistance(20, 0, 6*Math.PI));
//        sleep(400);
//        ThreadAction(new GlyphDeployOverTheBack());
//        runAction(new DriveToDistance(-20, 20, 5*Math.PI));
//        runAction(new GlyphRelease());
//        runAction(new DriveToDistance(4, Robot.getDrive().getGyroAngleDegrees()));

        finalAction();
    }
}