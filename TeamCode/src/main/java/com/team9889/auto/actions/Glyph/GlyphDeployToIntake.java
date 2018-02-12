package com.team9889.auto.actions.Glyph;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.auto.actions.Action;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 2/7/2018.
 */

public class GlyphDeployToIntake implements Action {
    private GlyphLypht mLift = Robot.getInstance().getLift();
    private Intake mIntake = Robot.getInstance().getIntake();
    private ElapsedTime t = new ElapsedTime();

    @Override
    public void start() {
        mLift.goTo(GlyphLypht.Mode.Intake);
        mIntake.deploy();
        t.reset();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return t.milliseconds()>1500;
    }

    @Override
    public void done() {
        mIntake.intake();
    }
}
