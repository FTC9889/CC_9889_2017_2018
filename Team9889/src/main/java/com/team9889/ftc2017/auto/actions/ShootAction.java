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
    private int mParticleCount;
    private int mParticlesShot;

    private boolean finished = false;

    private boolean displayed = false;

    private ElapsedTime shot = new ElapsedTime();

    /**
     *
     * @param numberOfParticles One or Two
     */
    public ShootAction(int numberOfParticles){
        mParticleCount = numberOfParticles;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }

    @Override
    public void done() {
        mFlywheel.WantedState(Flywheel.WantedState.OFF);
        mIntake.WantedState(Intake.WantedState.WANTS_STOP);
    }

    @Override
    public void start() {
        mIntake.WantedState(Intake.WantedState.WANTS_WAIT);
        mFlywheel.WantedState(Flywheel.WantedState.ON);
        shot.reset();
    }

    @Override
    public void update(LinearOpMode linearOpMode) {
        if(shot.milliseconds() < 1000){
            mIntake.WantedState(Intake.WantedState.WANTS_WAIT);

            if(!displayed){
                linearOpMode.telemetry.addData("Shot Action", "WAITS WAIT");
                displayed = true;
            }
        }

        if(shot.milliseconds() > 1000 && shot.milliseconds() <1500){
            mIntake.WantedState(Intake.WantedState.WANTS_SHOOT);
            if (displayed){
                linearOpMode.telemetry.addData("Shot Action", "WAITS SHOOT");
                displayed = false;
            }
        }

        if(mParticleCount == 2){
            if(shot.milliseconds() > 1500 && shot.milliseconds() <2000){
                mIntake.WantedState(Intake.WantedState.WANTS_WAIT);
                if (!displayed){
                    linearOpMode.telemetry.addData("Shot Action", "WAITS WAIT");
                    displayed = true;
                }
            }

            if(shot.milliseconds() > 2000 && shot.milliseconds() <2500){
                mIntake.WantedState(Intake.WantedState.WANTS_SHOOT);
                if (displayed){
                    linearOpMode.telemetry.addData("Shot Action", "WAITS SHOOT");
                    displayed = false;
                }
            }

            if (shot.milliseconds() > 2500){
                finished = true;
            }
        }else if(shot.milliseconds() > 1500){
            finished = true;
        }
    }
}
