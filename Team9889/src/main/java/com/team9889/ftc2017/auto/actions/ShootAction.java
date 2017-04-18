package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.ftc2017.subsystems.Flywheel;
import com.team9889.ftc2017.subsystems.Intake;

/**
 * Created by joshua on 4/17/17.
 */

public class ShootAction implements Action {
    private Flywheel mFlywheel = Flywheel.getInstance();
    private Intake mIntake = Intake.getInstance();

    private ElapsedTime shot = new ElapsedTime();

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        mIntake.WantedState(Intake.WantedState.WANTS_WAIT);
        mFlywheel.WantedState(Flywheel.WantedState.ON);
        shot.reset();
    }

    @Override
    public void update(LinearOpMode linearOpMode) {

    }
}
