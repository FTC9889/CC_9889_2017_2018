package com.team9889.ftc2017.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.ftc2017.*;
import com.team9889.ftc2017.auto.actions.*;

/**
 * Created by Joshua H on 4/10/2017.
 */

@Autonomous(name = "Red Close")
public class RED_CLOSE extends Team9889LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(hardwareMap,this);

        runAction(new DriveStraightAction(15, Constants.kDriveMinSpeed), this);

        runAction(new ShootAction(2), this);

        finalAction(this);
    }
}
