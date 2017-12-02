package com.team9889.auto.modes;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class RED_FOWARD {
    public RED_FOWARD(AutoModeBase M, RelicRecoveryVuMark column){
        // Drive off platform
        M.runAction(new DriveToPositionAction(inches2Ticks(17), inches2Ticks(17), 0.1, 0.1, 5));
        M.sleep(500);

        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        M.Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        // Turn 90 degrees
        M.Robot.getDrive().setLeftRightPower(0.6, -0.6);
        boolean turning = true;
        while (turning && M.opModeIsActive()){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < -90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(-0.35, 0.35);

        turning = true;
        while (turning && M.opModeIsActive()) {
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() > -90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(200);

        int left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(25);
        int right = M.Robot.getDrive().getRightTicks() + inches2Ticks(25);
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);

        while(M.opModeIsActive()){
            if(left < M.Robot.getDrive().getLeftTicks())
                break;

            if(right < M.Robot.getDrive().getRightTicks())
                break;

            if(M.Robot.getDrive().getGyroAngleDegrees() > -90)
                M.Robot.getDrive().setLeftRightPower(0.4, 0.2);
            else if(M.Robot.getDrive().getGyroAngleDegrees() < -90)
                M.Robot.getDrive().setLeftRightPower(0.2, 0.4);
            else
                M.Robot.getDrive().setLeftRightPower(0.4, 0.4);
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.Robot.getIntake().clearArm();
        M.Robot.getLift().clamp();
        M.sleep(200);
        M.Robot.getLift().goTo(GlyphLypht.Mode.Level2);
        M.sleep(500);
        M.Robot.getIntake().retract();

        switch (column){
            case LEFT:
                M.Robot.getDrive().setLeftRightPower(0.2, -0.2);
                turning = true;
                while (turning && M.opModeIsActive()) {
                    M.updateTelemetry();
                    if (M.Robot.getDrive().getGyroAngleDegrees() < -100)
                        turning = false;
                    M.idle();
                }

                M.Robot.getDrive().setLeftRightPower(0,0);
                M.sleep(100);

                left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(4);
                right = M.Robot.getDrive().getRightTicks() + inches2Ticks(4);

                while(M.opModeIsActive()){
                    if(left < M.Robot.getDrive().getLeftTicks())
                        break;

                    if(right < M.Robot.getDrive().getRightTicks())
                        break;

                    if(M.Robot.getDrive().getGyroAngleDegrees() > -100)
                        M.Robot.getDrive().setLeftRightPower(0.4, 0.2);
                    else if(M.Robot.getDrive().getGyroAngleDegrees() < -100)
                        M.Robot.getDrive().setLeftRightPower(0.2, 0.4);
                    else
                        M.Robot.getDrive().setLeftRightPower(0.4, 0.4);
                }
                M.Robot.getDrive().setLeftRightPower(0,0);
                M.sleep(500);
                break;
            case CENTER:
                M.Robot.getDrive().setLeftRightPower(0.2, -0.2);
                turning = true;
                while (turning && M.opModeIsActive()) {
                    M.updateTelemetry();
                    if (M.Robot.getDrive().getGyroAngleDegrees() < -110)
                        turning = false;
                    M.idle();
                }

                M.Robot.getDrive().setLeftRightPower(0,0);
                M.sleep(100);

                left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(7);
                right = M.Robot.getDrive().getRightTicks() + inches2Ticks(7);

                while(M.opModeIsActive()){
                    if(left < M.Robot.getDrive().getLeftTicks())
                        break;

                    if(right < M.Robot.getDrive().getRightTicks())
                        break;

                    if(M.Robot.getDrive().getGyroAngleDegrees() > -110)
                        M.Robot.getDrive().setLeftRightPower(0.2, 0.1);
                    else if(M.Robot.getDrive().getGyroAngleDegrees() < -110)
                        M.Robot.getDrive().setLeftRightPower(0.1, 0.2);
                    else
                        M.Robot.getDrive().setLeftRightPower(0.2, 0.2);
                }
                M.Robot.getDrive().setLeftRightPower(0,0);
                M.sleep(400);

                break;
            case RIGHT:
                M.Robot.getDrive().setLeftRightPower(0.2, -0.2);
                turning = true;
                while (turning && M.opModeIsActive()) {
                    M.updateTelemetry();
                    if (M.Robot.getDrive().getGyroAngleDegrees() < -120)
                        turning = false;
                    M.idle();
                }

                M.Robot.getDrive().setLeftRightPower(0,0);
                M.sleep(100);

                left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(14);
                right = M.Robot.getDrive().getRightTicks() + inches2Ticks(14);

                while(M.opModeIsActive()){
                    if(left < M.Robot.getDrive().getLeftTicks())
                        break;

                    if(right < M.Robot.getDrive().getRightTicks())
                        break;

                    if(M.Robot.getDrive().getGyroAngleDegrees() > -120)
                        M.Robot.getDrive().setLeftRightPower(0.2, 0.1);
                    else if(M.Robot.getDrive().getGyroAngleDegrees() < -120)
                        M.Robot.getDrive().setLeftRightPower(0.1, 0.2);
                    else
                        M.Robot.getDrive().setLeftRightPower(0.2, 0.2);
                }
                M.Robot.getDrive().setLeftRightPower(0,0);
                M.sleep(400);

                break;
        }

        M.Robot.getLift().release();
        M.sleep(1000);
        M.Robot.getDrive().setLeftRightPower(-1, -1);
        M.sleep(500);
        M.Robot.getDrive().setLeftRightPower(0,0);
        M.sleep(100);
        M.Robot.getLift().goTo(GlyphLypht.Mode.Intake);
        M.Robot.getIntake().retract();
        M.Robot.getIntake().stopIntake();
        M.sleep(500);
        M.Robot.getDrive().setLeftRightPower(0.4, 0.4);
        M.sleep(750);
        M.Robot.getDrive().setLeftRightPower(0,0);
        M.sleep(100);
        M.Robot.getDrive().setLeftRightPower(-0.1, -0.1);
        M.sleep(300);
        M.Robot.getDrive().setLeftRightPower(0,0);
    }
}
