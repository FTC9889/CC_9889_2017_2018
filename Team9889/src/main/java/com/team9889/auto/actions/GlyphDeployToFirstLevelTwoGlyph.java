package com.team9889.auto.actions;

import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/12/2018.
 */

public class GlyphDeployToFirstLevelTwoGlyph implements Action {

    private Robot robot = Robot.getInstance();
    private Intake mInake = robot.getIntake();
    private GlyphLypht mLift = robot.getLift();

    @Override
    public void start() {
        mInake.clearArm();

        mLift.clamp();
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {}

        mLift.goTo(GlyphLypht.Mode.Level2);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        mInake.retract();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {

    }
}
