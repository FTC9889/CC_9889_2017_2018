package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class IntakeSwivel implements Action {
    private ElapsedTime t = new ElapsedTime();

    @Override
    public void start(Team9889Linear opMode) {
        t.reset();
    }

    @Override
    public void update(Team9889Linear opMode) {
        if(t.milliseconds()<250)
            opMode.Robot.getIntake().leftRetract();
        else if(t.milliseconds()<500)
            opMode.Robot.getIntake().rightRetract();
        else
            opMode.Robot.getIntake().intake();
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>=500;
    }

    @Override
    public void done() {}
}
