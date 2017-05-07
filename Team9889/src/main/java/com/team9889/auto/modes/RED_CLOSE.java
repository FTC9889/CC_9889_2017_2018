package com.team9889.ftc2017.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.ftc2017.*;
import com.team9889.ftc2017.auto.actions.*;

import java.util.ArrayList;

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

        runAction(new DriveTimeAction(5, Constants.kDriveMinSpeed), this);

        runAction(new WaitAction(1000), this);

        ArrayList<Action> DriveAndShoot = new ArrayList<>();
        DriveAndShoot.add(new DriveStraightAction(13, Constants.kDriveMaxSpeed));
        DriveAndShoot.add(new ShootAction(1));

        runAction(new ParallelAction(DriveAndShoot), this);

        finalAction(this);
    }
}
