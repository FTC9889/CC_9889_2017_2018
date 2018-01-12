package com.team9889.auto.actions;

import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToFirstLevel implements Action {
    private Robot robot = Robot.getInstance();
    private Intake mIntake = robot.getIntake();
    private GlyphLypht mLift = robot.getLift();

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {
        mIntake.clearArm();

        mLift.clamp();
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {}

        mLift.goTo(GlyphLypht.Mode.Level2);
        mLift.setServoPosition(0.2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }

    @Override
    public void update() {}

    @Override
    public void done() {
        mIntake.retract();
        mIntake.stopIntake();
    }
}
