package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Intake;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class IntakeDeployAndCollect implements Action {
    private Intake mIntake;

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mIntake = opMode.Robot.getIntake();
        mIntake.intake();
        opMode.sleep(100);
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public void done() {

    }
}
