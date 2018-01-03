package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class IntakeDeployWideIntake implements Action {

    private ElapsedTime t = new ElapsedTime();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>250;
    }

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().autoIntake();
        t.reset();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public void done() {

    }
}
