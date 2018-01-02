package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToFirstLevel implements Action {

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().clearArm();
        opMode.Robot.getLift().clamp();
        opMode.sleep(200);
        opMode.Robot.getLift().goTo(GlyphLypht.Mode.Level2);
        opMode.Robot.getLift().setServoPosition(0.2);
        opMode.sleep(250);
        opMode.Robot.getIntake().retract();
        opMode.Robot.getIntake().stopIntake();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public void done() {

    }
}
