package com.team9889.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

/**
 * Created by joshua9889 on 4/10/2017.
 */

@Autonomous(name = "Red Close")
@Disabled
public class RED_CLOSE extends AutoModeBase{

    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(this, true);

        runAction(new DriveStraightAction(10, 0.5));

        finalAction();
    }
}
