package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 10/29/2017.
 */

public class Intake extends Subsystem{
    CRServo left, right;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {

    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        return false;
    }

    @Override
    public void stop() {

    }

    @Override
    public void zeroSensors() {

    }
}
