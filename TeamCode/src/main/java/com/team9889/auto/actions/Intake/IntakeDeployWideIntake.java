package com.team9889.auto.actions.Intake;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.auto.actions.Action;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class IntakeDeployWideIntake implements Action {

    private ElapsedTime t = new ElapsedTime();
    private Intake mIntake = Robot.getInstance().getIntake();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>250;
    }

    @Override
    public void start() {
        mIntake.twoGlyphSpecial();
        t.reset();
    }

    @Override
    public void update() {}

    @Override
    public void done() {}
}
