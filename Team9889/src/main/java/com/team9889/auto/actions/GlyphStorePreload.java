package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/2/2018.
 */

public class GlyphStorePreload implements Action {

    private Robot robot = Robot.getInstance();
    private Intake mIntake = robot.getIntake();
    private GlyphLypht mLift = robot.getLift();

    public GlyphStorePreload(){}

    @Override
    public void start() {
        mIntake.clearArm();
        sleep(500);
        mLift.clamp();
        mLift.setServoPosition(0.37);
        sleep(600);

        mLift.release();
        sleep(500);

        mIntake.leftRetract();
        sleep(300);
        mIntake.rightRetract();
        sleep(300);
        mIntake.leftRetract();
        sleep(300);
        mIntake.clearArm();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {}

    private void sleep(int millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
        }
    }
}
