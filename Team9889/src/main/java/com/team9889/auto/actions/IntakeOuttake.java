package com.team9889.auto.actions;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Intake;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/5/2018.
 */

public class IntakeOuttake implements Action {
    private Robot robot = Robot.getInstance();
    private Intake mIntake = robot.getIntake();

    public IntakeOuttake(){}

    @Override
    public void start() {
        mIntake.outtake();
        sleep(500);
        mIntake.clearArm();
    }

    @Override
    public void update() {}

    @Override
    public boolean isFinished() {return true;}

    @Override
    public void done() {}

    private void sleep(int millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
        }
    }
}
