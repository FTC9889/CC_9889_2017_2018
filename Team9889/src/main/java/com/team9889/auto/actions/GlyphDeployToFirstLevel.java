package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToFirstLevel implements Action {
    private Intake mIntake;

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mIntake = opMode.Robot.getIntake();
        mIntake.clearArm();

        opMode.Robot.getLift().clamp();
        opMode.sleep(350);

        opMode.Robot.getLift().goTo(GlyphLypht.Mode.Level2);
        opMode.Robot.getLift().setServoPosition(0.2);
        opMode.sleep(1000);
    }

    @Override
    public void update(Team9889Linear opMode) {}

    @Override
    public void done() {
        mIntake.retract();
        mIntake.stopIntake();
    }
}
