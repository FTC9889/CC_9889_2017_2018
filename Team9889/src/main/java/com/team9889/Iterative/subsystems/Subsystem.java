package com.team9889.Iterative.subsystems;

import com.team9889.Iterative.Team9889OpMode;

/**
 * Created by Joshua on 7/29/2017.
 */

public interface Subsystem {
    void init(Team9889OpMode team9889OpMode);
    void stop(Team9889OpMode team9889OpMode);
    void outputTelemetry(Team9889OpMode team9889OpMode);
}
