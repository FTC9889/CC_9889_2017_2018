package com.team9889.Iterative.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by joshua on 6/21/17.
 */

public interface Action {
    //Check and see if Action is still running
    boolean isFinished();

    //Called once before the Action is updated
    void start(OpMode opMode);

    //Called in a continuously after start
    void update(OpMode opMode);

    //Called after the Action is finished
    void done();
}
