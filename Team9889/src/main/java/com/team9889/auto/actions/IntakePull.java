package com.team9889.auto.actions;

import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class IntakePull implements Action {
    private Robot robot = Robot.getInstance();
    private Intake mIntake = robot.getIntake();

    @Override
    public void start() {
        mIntake.twoGlyphSpecial();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {}
}
