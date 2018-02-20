package com.team9889.subsystems;

import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 4/10/2017.
 *
 * The Subsystem abstract class serves as a basic framework for all robot
 * subsystems. Each subsystem outputs information to Telemetry, has a stop
 * routine (for after each match), and a routine to zero all sensors, which
 * helps with calibration.
 *
 * Each Subsystem is responsible for instantializing all member components
 * at the start of the match.
 */
public abstract class Subsystem {
    public abstract void outputToTelemetry(Telemetry telemetry);

    public abstract boolean init(Team9889Linear team9889Linear, boolean auton);

    public abstract void stop();

    public abstract void zeroSensors();

    public abstract void test(Telemetry telemetry);
}
