package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphRetractArm implements Action {
    private ElapsedTime t = new ElapsedTime();
    private Robot robot = Robot.getInstance();
    private GlyphLypht mLift = robot.getLift();
    private Intake mIntake = robot.getIntake();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>450;
    }

    @Override
    public void start() {
        t.reset();
    }

    @Override
    public void update() {
        if(t.milliseconds()<200){
            mLift.release();
            mIntake.clearArm();
            mIntake.stop();
        } else if(t.milliseconds()<450){
            mLift.goTo(GlyphLypht.Mode.Intake);
            mIntake.retract();
        }
    }

    @Override
    public void done() {}
}
