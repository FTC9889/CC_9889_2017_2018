package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 4/10/2017.
 *
 * Base Class for an Action
 *
 */

public interface Action {

    //Called once before the Action is updated
    void start(Team9889Linear opMode);

    //Called in a continuously after start
    void update(Team9889Linear opMode);

    //Check and see if Action is still running
    boolean isFinished();
    
    //Called after the Action is finished
    void done();
}
