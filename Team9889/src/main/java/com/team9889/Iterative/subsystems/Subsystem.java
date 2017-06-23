package com.team9889.Iterative.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by joshua on 6/21/17.
 */

public interface Subsystem {
    public abstract void outputToTelemtry(OpMode opMode);

    public abstract void init(OpMode opMode, boolean auton);

    public abstract void stop();

    public abstract void zeroSensors();
}
