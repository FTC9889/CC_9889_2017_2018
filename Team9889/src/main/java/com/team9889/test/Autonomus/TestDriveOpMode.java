package com.team9889.test.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.auto.actions.JewelHitColor;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 11/9/2017.
 */

@Autonomous(name = "testDrive")
@Disabled
public class TestDriveOpMode extends AutoModeBase {

    @Override
    public void runOpMode(){

        waitForStart(this, true);

        //Hit Jewel off platform
        if(alliance == "Red")
            runAction(new JewelHitColor(JewelColor.Red, this));
        else if(alliance == "Blue")
            runAction(new JewelHitColor(JewelColor.Blue, this));

        // Drive off platform
        runAction(new DriveToPositionAction(1307, 1307, 0.1, 0.1, 5));
        sleep(200);

        this.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        this.Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        // Turn 90 degrees
        this.Robot.getDrive().setLeftRightPower(-0.6, 0.6);
        boolean turning = true;
        while (turning){
            updateTelemetry();
            if (this.Robot.getDrive().getGyroAngleDegrees() > 90)
                turning = false;
            idle();
        }

        this.Robot.getDrive().setLeftRightPower(0.35, -0.35);

        turning = true;
        while (turning) {
            updateTelemetry();
            if (this.Robot.getDrive().getGyroAngleDegrees() < 90)
                turning = false;
            idle();
        }

        this.Robot.getDrive().setLeftRightPower(-0.35, 0.35);

        turning = true;
        while (turning){
            updateTelemetry();
            if (this.Robot.getDrive().getGyroAngleDegrees() > 90)
                turning = false;
            idle();
        }

        this.Robot.getDrive().setLeftRightPower(0,0);

        sleep(200);
        runAction(new DriveToPositionAction(1307, 1307, 0.1, 0.1, 5));
        finalAction();
    }
}
