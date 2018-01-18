package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphDeployToFirstLevel implements Action {
    private Robot robot = Robot.getInstance();
    private Intake mIntake = robot.getIntake();
    private GlyphLypht mLift = robot.getLift();
    private ElapsedTime t = new ElapsedTime();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>1350;
    }

    @Override
    public void start() {
        mIntake.clearArm();
        mLift.clamp();
        t.reset();
    }

    @Override
    public void update() {
        if(t.milliseconds()>350){
            mLift.goTo(GlyphLypht.Mode.Level2);
            mLift.setServoPosition(0.2);
        }
    }

    @Override
    public void done() {
        mIntake.retract();
        mIntake.stopIntake();
    }
}
