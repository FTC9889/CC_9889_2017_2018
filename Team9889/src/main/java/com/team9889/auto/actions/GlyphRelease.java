package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphRelease implements Action {

    private GlyphLypht mLift = Robot.getInstance().getLift();
    private ElapsedTime t = new ElapsedTime();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>400;
    }

    @Override
    public void start() {
        mLift.release();
        t.reset();
    }

    @Override
    public void update() {}

    @Override
    public void done() {}
}
