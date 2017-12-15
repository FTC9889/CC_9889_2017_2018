package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 4/10/2017.
 *
 * Base Class for an Action
 *
 */

public interface Action {
    //Check and see if Action is still running
    public abstract boolean isFinished();

    //Called once before the Action is updated
    public abstract void start(Team9889Linear opMode);

    //Called in a continuously after start
    public abstract void update(Team9889Linear linearOpMode);
    
    //Called after the Action is finished
    public abstract void done();
}
