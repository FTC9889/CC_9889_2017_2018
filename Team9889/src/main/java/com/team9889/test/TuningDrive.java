package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;

/**
 * Created by joshua9889 on 1/6/2018.
 */

@Autonomous
@Disabled
public class TuningDrive extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, true, false);

//        runAction(new DriveToDistance(19, 0));
//        runAction(new TurnToAngle(-67));
//        ThreadAction(new GlyphDeployToFirstLevel());
//        runAction(new DriveToDistance(-28, -67));
//        runAction(new TurnToAngle(-180));
//        runAction(new DriveToDistance(17, -180));
//        runAction(new GlyphRelease());
//        runAction(new DriveToDistance(-5, -180, Math.PI));
//        runAction(new DriveToDistance(5, -180));
//        runAction(new DriveToDistance(-3, -180));
    }
}
