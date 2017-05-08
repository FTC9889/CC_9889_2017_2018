package com.team9889.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.subsystems.*;

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
    public abstract void start(HardwareMap hardwareMap);

    //Called in a continuously after start
    public abstract void update(LinearOpMode linearOpMode);
    
    //Called after the Action is finished
    public abstract void done();
}
