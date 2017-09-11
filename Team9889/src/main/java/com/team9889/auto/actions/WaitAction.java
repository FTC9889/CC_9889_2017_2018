package com.team9889.auto.actions;

import com.team9889.Team9889LinearOpMode;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class WaitAction implements Action{
    long mSleep;
    boolean mFinished;

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void update(Team9889LinearOpMode linearOpMode) {

    }

    @Override
    public void done() {

    }

    @Override
    public void start(Team9889LinearOpMode opMode) {
        opMode.sleep(mSleep);
    }

    public WaitAction(long milliseconds){
        mSleep = milliseconds;
    }
}
