package com.team9889.ftc2017.auto.modes;

import com.team9889.ftc2017.Constants;
import com.team9889.ftc2017.auto.AutoModeBase;
import com.team9889.ftc2017.auto.actions.*;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class RED_CLOSE extends AutoModeBase{

    @Override
    public void runOpMode() throws InterruptedException {
        waitForBaseStart(hardwareMap, this);

        runAction(new DriveStraightAction(15, Constants.kDriveMinSpeed), this);

        runAction(new TurnToAngle(90, Constants.kDriveMinSpeed), this);

        finalAction(this);
    }
}
