package com.team9889.auto.modes;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.DriveToPositionAction;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_BACK {
    public BLUE_BACK(AutoModeBase M, RelicRecoveryVuMark column) {
        // Drive off platform
        M.runAction(new DriveToPositionAction(inches2Ticks(20), inches2Ticks(20), 0.1, 0.1, 5));
        M.sleep(200);

        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        M.Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);

        // Turn 90 degrees
        M.Robot.getDrive().setLeftRightPower(-0.6, 0.6);
        boolean turning = true;
        while (turning && M.opModeIsActive()){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() > 90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(0.4, -0.4);

        turning = true;
        while (turning && M.opModeIsActive()) {
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < 90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(-0.3, 0.3);

        turning = true;
        while (turning && M.opModeIsActive()) {
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() > 90)
                turning = false;
            M.idle();
        }

        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(200);
		
		// Drive Straight 10"
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);

        int left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(10);
        int right = M.Robot.getDrive().getRightTicks() + inches2Ticks(10);

        while(M.opModeIsActive()){
            if(left < M.Robot.getDrive().getLeftTicks())
                break;

            if(right < M.Robot.getDrive().getRightTicks())
                break;

            if(M.Robot.getDrive().getGyroAngleDegrees() < 90)
                M.Robot.getDrive().setLeftRightPower(0.2, 0.3);
            else if(M.Robot.getDrive().getGyroAngleDegrees() > 90)
                M.Robot.getDrive().setLeftRightPower(0.3, 0.2);
            else
                M.Robot.getDrive().setLeftRightPower(0.3, 0.3);
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(250);
		
		// Turn to 135 degrees
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);

        ElapsedTime timeOut = new ElapsedTime();
        M.Robot.getDrive().setLeftRightPower(-0.4, 0.4);
        turning = true;
        while (turning && M.opModeIsActive()){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() >= 135)
                turning = false;
            M.idle();
        }

        timeOut.reset();
        M.Robot.getDrive().setLeftRightPower(0.3, -0.3);
        turning = true;
        while (turning && M.opModeIsActive() && timeOut.milliseconds()<2000){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < 135)
                turning = false;
            M.idle();
        }

        timeOut.reset();
        M.Robot.getDrive().setLeftRightPower(-0.2, 0.2);
        turning = true;
        while (turning && M.opModeIsActive()&& timeOut.milliseconds()<2000){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() >= 135)
                turning = false;
            M.idle();
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(200);

		// Drive Straight 30" to cryptobox
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);

        left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(30);
        right = M.Robot.getDrive().getRightTicks() + inches2Ticks(30);

        while(M.opModeIsActive()){
            if(left < M.Robot.getDrive().getLeftTicks())
                break;

            if(right < M.Robot.getDrive().getRightTicks())
                break;

            if(M.Robot.getDrive().getGyroAngleDegrees() < 135)
                M.Robot.getDrive().setLeftRightPower(0.2, 0.3);
            else if(M.Robot.getDrive().getGyroAngleDegrees() > 135)
                M.Robot.getDrive().setLeftRightPower(0.3, 0.2);
            else
                M.Robot.getDrive().setLeftRightPower(0.3, 0.3);
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.sleep(100);

		// Turn to face cryptobox
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        turning = true;
        while (turning && M.opModeIsActive()){
            M.Robot.getDrive().setLeftRightPower(-0.4, 0.4);
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < -177)
                turning = false;
            M.idle();
            M.Robot.getDrive().setLeftRightPower(0,0);
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.Robot.getIntake().clearArm();
        M.Robot.getLift().clamp();
        M.sleep(200);
        M.Robot.getLift().goTo(GlyphLypht.Mode.Level2);
        M.sleep(500);
        M.Robot.getIntake().retract();

        M.Robot.getDrive().setLeftRightPower(0.35, -0.35);
        turning = true;
        while (turning && M.opModeIsActive()){
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() > 160)
                turning = false;
            M.idle();
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

		// Drive foward to depost glyph in box
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.SPEED);
        left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(10);
        right = M.Robot.getDrive().getRightTicks() + inches2Ticks(10);

        while(M.opModeIsActive()){
            if(left < M.Robot.getDrive().getLeftTicks())
                break;

            if(right < M.Robot.getDrive().getRightTicks())
                break;

            if(M.Robot.getDrive().getGyroAngleDegrees() < -1)
                M.Robot.getDrive().setLeftRightPower(0.2, 0.3);
            else if(M.Robot.getDrive().getGyroAngleDegrees() > 1)
                M.Robot.getDrive().setLeftRightPower(0.3, 0.2);
            else
                M.Robot.getDrive().setLeftRightPower(0.3, 0.3);
        }
        M.Robot.getDrive().setLeftRightPower(0,0);
        M.sleep(500);
		
		// Release glyph
        M.Robot.getLift().release();
        M.sleep(1000);
		
		// Back away
        M.Robot.getDrive().setLeftRightPower(-0.5, -0.5);
        M.sleep(500);
        M.Robot.getDrive().setLeftRightPower(0,0);
		
		// Pull everything in
        M.Robot.getLift().goTo(GlyphLypht.Mode.Intake);
        M.Robot.getIntake().retract();
        M.Robot.getIntake().stopIntake();
        M.sleep(500);
		
		// Push glyph all the way in
        M.Robot.getDrive().setLeftRightPower(0.2, 0.2);
        M.sleep(750);
        M.Robot.getDrive().setLeftRightPower(0,0);
        M.sleep(100);
		// Back away from glyph
        M.Robot.getDrive().setLeftRightPower(-0.1, -0.1);
        M.sleep(300);
        M.Robot.getDrive().setLeftRightPower(0,0);
    }
}
