package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class IntakePull implements Action {
    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().twoGlyphSpecial();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {

    }
}
