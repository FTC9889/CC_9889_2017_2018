package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Jewel.JewelHitColor;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 2/12/2018.
 */

@Autonomous
@Disabled
public class TunePID extends AutoModeBase{
    @Override
    public void runOpMode() {
        waitForStart(true, false);

        this.runAction(new JewelHitColor(JewelColor.Blue, this));
    }
}