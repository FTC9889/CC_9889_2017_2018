package com.team9889.auto.modes.SingleGlyph;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;
import com.team9889.subsystems.GlyphLypht;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 4/10/2017.
 */

public class RED_BACK{

    public RED_BACK(AutoModeBase M, RelicRecoveryVuMark column){
        /// Drive off platform
        M.runAction(new DriveToDistance(20, 0));

        M.runAction(new TurnToAngle(-90));

        M.runAction(new DriveToDistance(10, -90));

        M.runAction(new TurnToAngle(-135));

        M.runAction(new DriveToDistance(30, -135));

        M.runAction(new TurnToAngle(177));


        // Deploy arm w/glyph inside
        M.runAction(new GlyphDeployToFirstLevel());

        M.runAction(new TurnToAngle(-160));

        M.runAction(new DriveToDistance(5, -160));

        M.runAction(new GlyphRelease());

        M.sleep(1000);
        M.Robot.getDrive().setLeftRightPower(-0.5, -0.5);
        M.sleep(500);
        M.Robot.getDrive().setLeftRightPower(0,0);
        M.Robot.getLift().goTo(GlyphLypht.Mode.Intake);
        M.Robot.getIntake().retract();
        M.Robot.getIntake().stopIntake();
        M.sleep(500);
        M.Robot.getDrive().setLeftRightPower(0.2, 0.2);
        M.sleep(750);
        M.Robot.getDrive().setLeftRightPower(0,0);
        M.sleep(100);
        M.Robot.getDrive().setLeftRightPower(-0.1, -0.1);
        M.sleep(300);
        M.Robot.getDrive().setLeftRightPower(0,0);
    }
}
