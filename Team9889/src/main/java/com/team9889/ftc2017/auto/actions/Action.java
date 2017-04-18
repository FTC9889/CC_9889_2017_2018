package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Joshua H on 4/10/2017.
 */

public interface Action {
    public abstract boolean isFinished();

    public abstract void done();

    public abstract void start();

    public abstract void update(LinearOpMode linearOpMode);
}
