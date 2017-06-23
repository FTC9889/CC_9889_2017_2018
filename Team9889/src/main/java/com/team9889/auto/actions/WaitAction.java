package com.team9889.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
            linearOpMode.idle();
        }
    }

    @Override
    public void done() {

    }

    @Override
    public void start(HardwareMap hardwareMap) {
        mStartTime = System.currentTimeMillis();
    }

    public WaitAction(long milliseconds){
        mTimetoWait = milliseconds;
    }
}
