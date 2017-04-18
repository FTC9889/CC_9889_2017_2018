package com.team9889.ftc2017.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Joshua H on 4/10/2017.
 *
 * The Subsystem abstract class, which serves as a basic framework for all robot
 * subsystems. Each subsystem outputs commands to SmartDashboard, has a stop
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
    public abstract void outputToTelemtry(LinearOpMode opMode);

    public abstract void init(HardwareMap hardwareMap, boolean auton);

    public abstract void stop();

    public abstract void zeroSensors();
}
