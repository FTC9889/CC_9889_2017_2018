package com.team9889.auto.modes;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_BACK {
    public void runOpMode(AutoModeBase M){
        // Drive off platform
        M.runAction(new DriveToPositionAction(1400, 1400, 0.1, 0.1, 5));
        M.sleep(200);

        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        M.Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        // Turn 45 degrees
        M.Robot.getDrive().setLeftRightPower(0.6, 0);
        boolean turning = true;
        while (turning){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < -45)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(-0.35, 0);

        turning = true;
        while (turning) {
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() > -45)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(200);

        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);
        M.Robot.getDrive().setLeftRightPower(-0.2, -0.2);
        M.sleep(2000);
        M.Robot.getDrive().setLeftRightPower(0,0);
    }
}