package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class IntakeSwivel implements Action {
    private ElapsedTime t = new ElapsedTime();
    private Robot robot = Robot.getInstance();
    private Intake mIntake = robot.getIntake();

    @Override
    public void start() {
        t.reset();
    }

    @Override
    public void update() {
        if(t.milliseconds()<250)
            mIntake.leftRetract();
        else if(t.milliseconds()<500)
            mIntake.rightRetract();
        else
            mIntake.intake();
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>=500;
    }

    @Override
    public void done() {}
}
