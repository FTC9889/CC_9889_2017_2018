package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToSecondLevel implements Action {
    private Robot robot = Robot.getInstance();
    private GlyphLypht mLift = robot.getLift();

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {
        mLift.clamp();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {}

        mLift.goTo(GlyphLypht.Mode.Level2);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {}

        mLift.goTo(GlyphLypht.Mode.Level4);
    }

    @Override
    public void update() {}

    @Override
    public void done() {}
}
