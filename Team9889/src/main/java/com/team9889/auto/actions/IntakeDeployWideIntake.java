package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class IntakeDeployWideIntake implements Action {
    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        opMode.Robot.getIntake().autoIntake();
        opMode.sleep(250);
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }
}
