package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

import static java.lang.Math.*;

/**
 * Created by joshua9889 on 12/30/2017.
 * OpMode to test stuff
 */

@Autonomous
@Disabled
public class FixBrokenThings extends AutoModeBase {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

        // Hit Jewel off
        jewel_Color = JewelColor.Blue;
        runAction(new JewelHitColor(JewelColor.Blue));

        runAction(new DriveToDistance(18, 0));
        runAction(new GlyphStorePreload());
        runAction(new IntakeDeployAndCollect());
        runAction(new TurnToAngle(-45));
        runAction(new DriveToDistance(12, -45, 5*Math.PI));
        sleep(1000);
        Robot.getLift().setServoPosition(0.4);
        Robot.getLift().clamp();
        Robot.getIntake().stopIntake();
        sleep(400);
        runAction(new DriveToDistance(-12, -45));
        runAction(new TurnToAngle(93));

        ThreadAction(new GlyphDeployToFirstLevelTwoGlyph());
        runAction(new DriveTimeAction(2000, 2*Math.PI, 93));
        runAction(new GlyphRelease());
        runAction(new DriveToDistance(-4, 93, Math.PI));


    }
}
