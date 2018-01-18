package com.team9889.subsystems;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 4/10/2017.
 *
 * The Subsystem abstract class, which serves as a basic framework for all robot
 * subsystems. Each subsystem outputs commands to Telemetry, has a stop
 * routine (for after each match), and a routine to zero all sensors, which
 * helps with calibration.
 *
 * All Subsystems only have one instance (after all, one robot does not have two
 * drivetrains), and functions get the instance of the drivetrain and act
 * accordingly. Subsystems are also a state machine with a desired state and
 * actual state; the robot code will try to match the two states with actions.
 * Each Subsystem also is responsible for instantializing all member components
 * at the start of the match.
 */
public abstract class Subsystem {
    public abstract void outputToTelemetry(Team9889Linear opMode);

    public abstract boolean init(Team9889Linear team9889Linear, boolean auton);

    public abstract void stop();

    public abstract void zeroSensors();
}
