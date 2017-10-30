package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.Team9889LinearOpMode;
import com.team9889.auto.AutoModeBase;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 10/29/2017.
 */

@Autonomous (name = "TestAuton")
public class TestAuto extends Team9889LinearOpMode{
    @Override
    public void runOpMode() {
        waitForTeamStart(this, false);

        Robot.getJewel().deploy();
        sleep(800);
        Robot.getJewel().left();
        sleep(300);
        Robot.getJewel().retract();

        Robot.getDrive().zeroSensors();
        Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
        Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);

        while (opModeIsActive() && Robot.getDrive().getLeftDistanceInches() < 27) {
            Robot.getDrive().setLeftRightPower(0.05, 0.05);
            updateTelemetry();
            idle();
        }

        Robot.stop();


    }
}
