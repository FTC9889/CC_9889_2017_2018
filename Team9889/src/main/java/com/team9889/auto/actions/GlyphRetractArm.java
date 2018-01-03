package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Intake;

/**
 * Created by joshua9889 on 12/12/2017.
 */

public class GlyphRetractArm implements Action {
    private ElapsedTime t = new ElapsedTime();

    @Override
    public boolean isFinished() {
        return t.milliseconds()>450;
    }

    @Override
    public void start(Team9889Linear opMode) {
        t.reset();
    }

    @Override
    public void update(Team9889Linear opMode) {
        if(t.milliseconds()<200){
            opMode.Robot.getLift().release();
            opMode.Robot.getIntake().clearArm();
            opMode.Robot.getIntake().stop();
        } else if(t.milliseconds()<450){
            opMode.Robot.getLift().goTo(GlyphLypht.Mode.Intake);
            opMode.Robot.getIntake().retract();
        }
    }

    @Override
    public void done() {}
}
