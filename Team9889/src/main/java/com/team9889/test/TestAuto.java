package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

/**
 * Test Auto Mode
 * Created by joshua9889 on 10/29/2017.
 */

@Autonomous (name = "Red")
public class TestAuto extends Team9889LinearOpMode{
    @Override
    public void runOpMode() {
        waitForTeamStart(this, true);
        Robot.getLift().goTo(GlyphLypht.Mode.Level1);

        if (jewel_Color != null){
            Robot.getJewel().deploy();
            sleep(830);
            if (jewel_Color == JewelColor.Red)
                Robot.getJewel().left();
            else if (jewel_Color == JewelColor.Blue)
                Robot.getJewel().right();
            sleep(350);
        }
        Robot.getJewel().retract();

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.POSITION);

        Robot.getLift().setPosition(0, 0.2);
        sleep(1000);
        finalAction();
    }
}
