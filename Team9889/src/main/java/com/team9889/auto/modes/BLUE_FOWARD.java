package com.team9889.auto.modes;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.subsystems.Drive;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_FOWARD {
    public BLUE_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        // Drive off platform
        M.runAction(new DriveToPositionAction(1300, 1300, 0.1, 0.1, 5));
        M.sleep(200);

        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        M.Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        // Turn 90 degrees
        M.Robot.getDrive().setLeftRightPower(0.6, -0.6);
        boolean turning = true;
        while (turning){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < -90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(-0.35, 0.35);

        turning = true;
        while (turning) {
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() > -90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(200);

        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);
        M.Robot.getDrive().setLeftRightPower(-0.2, -0.2);
        M.sleep(1000);
        M.Robot.getDrive().setLeftRightPower(0,0);

    }
}
