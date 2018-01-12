package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 1/2/2018.
 */

public class GlyphStorePreload implements Action {

    public GlyphStorePreload(){}

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().clearArm();
        opMode.sleep(500);
        opMode.Robot.getLift().clamp();
        opMode.Robot.getLift().setServoPosition(0.37);
        opMode.sleep(600);

        opMode.Robot.getLift().release();
        opMode.sleep(500);

        opMode.Robot.getIntake().leftRetract();
        opMode.sleep(300);
        opMode.Robot.getIntake().rightRetract();
        opMode.sleep(300);
        opMode.Robot.getIntake().leftRetract();
        opMode.sleep(300);
        opMode.Robot.getIntake().clearArm();
    }

    @Override
    public void update(Team9889Linear opMode) {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {}
}
