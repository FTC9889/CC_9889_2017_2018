package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToSecondLevel implements Action {
    private GlyphLypht mLift;
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mLift = opMode.Robot.getLift();
        mLift.clamp();
        opMode.sleep(200);
        mLift.goTo(GlyphLypht.Mode.Level2);
        opMode.sleep(250);
        mLift.goTo(GlyphLypht.Mode.Level4);
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public void done() {

    }
}
