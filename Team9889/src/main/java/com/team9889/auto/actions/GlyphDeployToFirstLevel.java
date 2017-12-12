package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToFirstLevel implements Action {
    private GlyphLypht mLift;
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        mLift = opMode.Robot.getLift();
        mLift.clamp();
        opMode.sleep(200);
        mLift.goTo(GlyphLypht.Mode.Level2);
        opMode.sleep(250);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }
}
