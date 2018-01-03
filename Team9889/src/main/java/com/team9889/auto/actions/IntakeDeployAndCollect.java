package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Intake;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class IntakeDeployAndCollect implements Action {

    private ElapsedTime t = new ElapsedTime();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>100;
    }

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().intake();
        t.reset();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {}

    @Override
    public void done() {}
}
