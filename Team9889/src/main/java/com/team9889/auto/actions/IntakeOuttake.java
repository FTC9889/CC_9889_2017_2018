package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 1/5/2018.
 */

public class IntakeOuttake implements Action {

    public IntakeOuttake(){}

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().outtake();
        opMode.sleep(500);
        opMode.Robot.getIntake().clearArm();
    }

    @Override
    public void update(Team9889Linear opMode) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void done() {

    }
}
