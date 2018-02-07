package com.team9889.auto.actions.Glyph;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.auto.actions.Action;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 2/6/2018.
 */

public class GlyphDeployOverTheBack implements Action {
    private GlyphLypht mLift = Robot.getInstance().getLift();
    private Intake mIntake = Robot.getInstance().getIntake();
    private ElapsedTime t = new ElapsedTime();

    @Override
    public void start() {
        mIntake.clearArm();
        mLift.clamp();
        t.reset();
    }

    @Override
    public void update() {
        if(t.milliseconds()>250){
            mLift.goTo(GlyphLypht.Mode.OvertheBack);
        }
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>2000 || !mLift.isAtLocation();
    }

    @Override
    public void done() {

    }
}
