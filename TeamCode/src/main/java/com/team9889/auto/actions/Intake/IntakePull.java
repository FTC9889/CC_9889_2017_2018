package com.team9889.auto.actions.Intake;

import com.team9889.auto.actions.Action;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/1/2018.
 */

public class IntakePull implements Action {
    @Override
    public void start() {
        Robot.getInstance().getIntake().twoGlyphSpecial();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {}
}
