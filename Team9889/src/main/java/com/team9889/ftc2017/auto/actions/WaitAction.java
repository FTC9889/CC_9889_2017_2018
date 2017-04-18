package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.sql.Time;
import java.util.Timer;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class WaitAction implements Action{
    long mTimetoWait;
    long mStartTime;

    @Override
    public boolean isFinished() {
        return System.currentTimeMillis() - mStartTime >= mTimetoWait;
    }

    @Override
    public void update(LinearOpMode linearOpMode) {
        while (linearOpMode.opModeIsActive() && !linearOpMode.isStopRequested()){

        }
    }

    @Override
    public void done() {

    }

    @Override
    public void start() {
        mStartTime = System.currentTimeMillis();
    }

    public WaitAction(long milliseconds){
        mTimetoWait = milliseconds;
    }
}
