package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Joshua H on 4/10/2017.
 *
 * Base Class for an Action
 *
 */

public interface Action {
    //Check and see if Action is still running
    public abstract boolean isFinished();

    //Called once before the Action is updated
    public abstract void start();

    //Called in a continuously after start
    public abstract void update(LinearOpMode linearOpMode);
    
    //Called after the Action is finished
    public abstract void done();
}
