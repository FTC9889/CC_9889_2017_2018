package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 1/12/2018.
 */

public class GlyphDeployToFirstLevelTwoGlyph implements Action {
    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().clearArm();

        opMode.Robot.getLift().clamp();
        opMode.sleep(350);

        opMode.Robot.getLift().goTo(GlyphLypht.Mode.Level2);
        opMode.sleep(1000);
    }

    @Override
    public void update(Team9889Linear opMode) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {

    }
}
