package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;
import com.team9889.subsystems.Drive;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import static com.team9889.Constants.inches2Ticks;

/**
 * Created by joshua9889 on 11/24/2017.
 */

public class BLUE_BACK {
    public BLUE_BACK(AutoModeBase M, RelicRecoveryVuMark column) {
        // Drive off platform
        M.runAction(new DriveToDistance(20, 0));
        M.sleep(250);

        M.runAction(new TurnToAngle(90, 0.6));
        M.sleep(200);
		
		// Drive Straight 10"
        M.runAction(new DriveToDistance(10, 90));
        M.sleep(250);
		
		// Turn to 135 degrees
        M.runAction(new TurnToAngle(135, 0.6));

		// Drive Straight 30" to cryptobox
        M.runAction(new DriveToDistance(10, 135));
        M.sleep(100);

		// Turn to face cryptobox
        M.Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        boolean turning = true;
        while (turning && M.opModeIsActive()){
            M.Robot.getDrive().setLeftRightPower(-0.4, 0.4);
            M.updateTelemetry();
            if (M.Robot.getDrive().getGyroAngleDegrees() < -177)
                turning = false;
            M.idle();
            M.Robot.getDrive().setLeftRightPower(0,0);
        }
        M.Robot.getDrive().setLeftRightPower(0,0);

        M.runAction(new GlyphDeployToFirstLevel());

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
        int left = M.Robot.getDrive().getLeftTicks() + inches2Ticks(10);
        int right = M.Robot.getDrive().getRightTicks() + inches2Ticks(10);

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
        M.runAction(new GlyphRelease());
		
		// Back away
        M.runAction(new DriveTimeAction(500, -0.3));
		
		// Pull everything in
        M.runAction(new GlyphRetractArm());
		
		// Push glyph all the way in
        M.runAction(new DriveTimeAction(750, 0.2));
        M.sleep(100);

		// Back away from glyph
        M.runAction(new DriveTimeAction(300, -0.1));
    }
}
