package com.team9889.auto.actions.Intake;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.auto.actions.Action;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/5/2018.
 */

public class IntakeOuttake implements Action {
    private Intake mIntake = Robot.getInstance().getIntake();
    private ElapsedTime t = new ElapsedTime();

    public IntakeOuttake(){}

    @Override
    public void start() {
        t.reset();
    }

    @Override
    public void update() {
        mIntake.outtake();
    }

    @Override
    public boolean isFinished() {return t.milliseconds()>500;}

    @Override
    public void done() {
        mIntake.clearArm();
    }
}
