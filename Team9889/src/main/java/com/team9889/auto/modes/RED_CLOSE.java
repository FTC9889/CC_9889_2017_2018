package com.team9889.Linear.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.*;
import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.Linear.auto.AutoModeBase;
import com.team9889.Linear.auto.actions.*;

/**
 * Created by Joshua H on 4/10/2017.
 */

@Autonomous(name = "Red Close")
@Disabled
public class RED_CLOSE extends AutoModeBase{

    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(this);

        runAction(new DriveStraightAction(10, 0.5));

        finalAction();
    }
}
