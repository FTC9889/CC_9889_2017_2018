package com.team9889.auto;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by Jin on 11/10/2017.
 */
@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous extends AutoModeBase {

    @Override
    public void runOpMode() {

        waitForTeamStart(this, true);

        Robot.getLift().goTo(GlyphLypht.Mode.Level1);

        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red));
        else if (alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue));

        runAction(new DriveToPositionAction(1426, 1426, 0.1, 0.1));
    }


}
