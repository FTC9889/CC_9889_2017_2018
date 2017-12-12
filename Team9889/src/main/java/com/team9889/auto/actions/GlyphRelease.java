package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphRelease implements Action {
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        opMode.Robot.getLift().release();
        opMode.sleep(400);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }
}
