package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 1/2/2018.
 */

public class GlyphStorePreload implements Action {

    public GlyphStorePreload(){}

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getIntake().clearArm();
        opMode.sleep(300);
        opMode.Robot.getLift().release();
        opMode.sleep(400);

        opMode.Robot.getIntake().leftRetract();
        opMode.sleep(250);
        opMode.Robot.getIntake().rightRetract();
        opMode.sleep(250);
        opMode.Robot.getIntake().intake();

        opMode.Robot.getLift().goTo(GlyphLypht.Mode.Intake);
        opMode.Robot.getIntake().retract();
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
