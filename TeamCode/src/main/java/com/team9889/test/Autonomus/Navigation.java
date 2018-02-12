package com.team9889.test.Autonomus;

import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;

import static com.team9889.lib.CruiseLib.degreesToRadians;

/**
 * Created by joshua9889 on 9/13/2017.
 */

public class Navigation {
    double x = 0, y = 0, heading = 0;
    double lastL, lastR;
    double gyroReference = 0;
    private Drive mDrive = null;
    public Navigation(Drive Drive_){this.mDrive = Drive_;}

    public void resetWithReferenceHeading(double reference) {
        this.x = this.y = this.heading = 0;
        this.gyroReference = reference;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getHeading() {
        return this.heading;
    }

    public void update(double left, double right, double gyroAngle) {
        double diffL = left - this.lastL;
        double diffR = right - this.lastR;
        this.lastL = left;
        this.lastR = right;
        this.heading = gyroAngle - this.gyroReference;
        double magnitude = (diffL + diffR) / 2.0;
        this.x += magnitude * Math.sin(degreesToRadians(this.heading));
        this.y += magnitude * Math.cos(degreesToRadians(this.heading));
    }

    public void outputToTelemetry(Team9889Linear opMode) {
        String outputTelem = "X: " + this.x + " Y: " + this.y + " Heading: " + this.heading;
        opMode.telemetry.addData("Nav", outputTelem);
    }
}
