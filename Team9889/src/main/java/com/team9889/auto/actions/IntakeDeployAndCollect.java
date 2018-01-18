package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class IntakeDeployAndCollect implements Action {

    private ElapsedTime t = new ElapsedTime();
    private Intake mIntake = Robot.getInstance().getIntake();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>100;
    }

    @Override
    public void start() {
        mIntake.intake();
        t.reset();
    }

    @Override
    public void update() {}

    @Override
    public void done() {}
}
