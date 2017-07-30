package com.team9889.Linear.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.*;
import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.Linear.auto.actions.*;

/**
 * Created by Joshua H on 4/10/2017.
 */

@Autonomous(name = "Red Close")
public class RED_CLOSE extends Team9889LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //Set Properties to log in case of failure
        Constants.OpMode = "RED_CLOSE";
        Constants.Alliance = "RED";

        waitForTeamStart(this);

        runAction(new DriveStraightAction(10, 0.5));

        finalAction();
    }
}
