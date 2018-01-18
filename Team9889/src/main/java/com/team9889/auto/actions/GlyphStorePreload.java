package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
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
    private ElapsedTime t = new ElapsedTime();

    public GlyphStorePreload(){}

    @Override
    public void start() {
        t.reset();
    }

    @Override
    public void update() {
        if(t.milliseconds()<500){
            mIntake.clearArm();
        } else if(t.milliseconds()<1100){
            mLift.clamp();
            mLift.setServoPosition(0.37);
        } else if(t.milliseconds()<1600){
            mLift.release();
        } else if (t.milliseconds()<1900){
            mIntake.leftRetract();
        } else if (t.milliseconds()<2200){
            mIntake.rightRetract();
        } else if(t.milliseconds()<2500){
            mIntake.leftRetract();
        }
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>2500;
    }

    @Override
    public void done() {
        mIntake.clearArm();
    }
}
