package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class IntakeSwivel implements Action {
    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().leftRetract();
        opMode.sleep(250);
        opMode.Robot.getIntake().rightRetract();
        opMode.sleep(250);
        opMode.Robot.getIntake().intake();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {}
}
