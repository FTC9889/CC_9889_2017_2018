package com.team9889.Linear.auto.actions;

import com.team9889.Linear.Team9889LinearOpMode;

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
    public abstract void start(Team9889LinearOpMode opMode);

    //Called in a continuously after start
    public abstract void update(Team9889LinearOpMode linearOpMode);
    
    //Called after the Action is finished
    public abstract void done();
}
