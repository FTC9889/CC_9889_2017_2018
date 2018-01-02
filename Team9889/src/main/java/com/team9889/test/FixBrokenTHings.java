package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

/**
 * Created by joshua9889 on 12/30/2017.
 * OpMode to test stuff
 */

@Autonomous
public class FixBrokenTHings extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

        jewel_Color = JewelColor.Red;
        runAction(new JewelHitColor(JewelColor.Red));

        runAction(new DriveToDistance(21, 0, 5*Math.PI/3));
        Robot.getJewel().stop();

        // Turn to the cb
        runAction(new TurnToAngle(-90));

        if(opModeIsActive()) {
            runAction(new GlyphDeployToFirstLevel());
            runAction(new DriveToDistance(30, -91, 5 * Math.PI / 2.4));
            runAction(new GlyphRelease());
        }

        runAction(new DriveToDistance(-10, -90));

        if(opModeIsActive()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runAction(new GlyphRetractArm());
                    runAction(new IntakeDeployAndCollect());
                    Robot.getJewel().stop();
                }
            }).start();
        }

        runAction(new TurnToAngle(60));
        runAction(new DriveToDistance(28, 60, 3*Math.PI));
        runAction(new IntakeDeployAndCollect());

        sleep(400);
        runAction(new IntakePull());
        sleep(250);
        runAction(new IntakeSwivel());
        runAction(new IntakeDeployAndCollect());
        runAction(new DriveToDistance(4, 60, 6*Math.PI));
        runAction(new IntakePull());
        sleep(100);

        runAction(new DriveToDistance(-26, 60));

        if(opModeIsActive()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runAction(new IntakeSwivel());
                }
            }).start();
        }

        runAction(new TurnToAngle(-115));

        if (opModeIsActive()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runAction(new GlyphDeployToFirstLevel());
                }
            }).start();
        }
        sleep(100);
        runAction(new DriveTimeAction(900, 5*Math.PI, -115));

        runAction(new GlyphRelease());

        if(opModeIsActive()) {
            runAction(new DriveToDistance(-2, -115));
            runAction(new DriveTimeAction(300, 4 * Math.PI, -115));
            runAction(new DriveToDistance(-3, -115));
        }
    }
}
