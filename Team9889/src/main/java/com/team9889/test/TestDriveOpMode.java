package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 11/9/2017.
 */

@Autonomous(name = "testDrive")
public class TestDriveOpMode extends AutoModeBase {

    @Override
    public void runOpMode(){

        waitForTeamStart(this, true);
        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red));
        else if(alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue));

        Robot.getLift().goTo(GlyphLypht.Mode.Level1);
        sleep(200);

        runAction(new DriveToPositionAction(1100, 1100, 0.1, 0.1, 5));
        sleep(200);
        runAction(new DriveToPositionAction(0, 1580, 0, 0.2, 5));
        sleep(200);
        runAction(new DriveToPositionAction(653, 653, 0.2, 0.2, 5));
        sleep(200);
        runAction(new DriveToPositionAction(0, 1580, 0, 0.2, 5));
        sleep(200);
        runAction(new DriveToPositionAction(653, 653, 0.1, 0.1));
        sleep(200);

        telemetry.addData("", Robot.getDrive().isFinishedRunningToPosition());
        telemetry.update();
        sleep(4000);
        finalAction();
    }
}
