package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.Team9889LinearOpMode;
import com.team9889.auto.AutoModeBase;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 10/29/2017.
 */

@Autonomous (name = "Red")
public class TestAuto extends Team9889LinearOpMode{
    @Override
    public void runOpMode() {
        waitForStartNoVuforia(this);

        Robot.getJewel().deploy();
        Robot.getLift().goTo(GlyphLypht.Mode.Level1);
        sleep(830);
        if (jewel_Color == JewelColor.Red)
            Robot.getJewel().left();
        else if (jewel_Color == JewelColor.Blue)
            Robot.getJewel().right();
        sleep(350);
        Robot.getJewel().retract();

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
        Robot.getDrive().setLeftRightPower(0.4, 0.4);
        sleep(500);
        Robot.getDrive().stop();

        finalAction();
    }
}
