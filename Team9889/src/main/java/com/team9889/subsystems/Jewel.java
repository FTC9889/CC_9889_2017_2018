package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 10/29/2017.
 */

public class Jewel extends Subsystem {

    private Servo arm, wrist;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.InternalopMode.telemetry.addData("Jewel Arm Pos", this.arm.getPosition());
        opMode.InternalopMode.telemetry.addData("Jewel Wrist Pos", this.wrist.getPosition());
    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {

        try {
            this.arm = team9889LinearOpMode.hardwareMap.servo.get(Constants.kJewelArmId);
            this.arm.setPosition(Constants.RetractedJewelArm);
        } catch (Exception e) {
            return false;
        }

        try {
            this.wrist = team9889LinearOpMode.hardwareMap.servo.get(Constants.kJewelWristId);
            this.wrist.setPosition(Constants.RightJewelWrist);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public void stop() {
        this.retract();
    }

    @Override
    public void zeroSensors() {

    }

    public void retract() {
        try {
            this.arm.setPosition(Constants.RetractedJewelArm);
            this.wrist.setPosition(Constants.CenterJewelWrist);
        } catch (Exception e) {}
    }

    public void deploy() {
        try {
            this.arm.setPosition(Constants.DeployedJewelArm);
            this.wrist.setPosition(Constants.CenterJewelWrist);
        } catch (Exception e){}
    }

    public void left(){
        try {
            this.wrist.setPosition(Constants.LeftJewelWrist);
        } catch (Exception e){}
    }

    public void right(){
        try {
            this.wrist.setPosition(Constants.RightJewelWrist);
        } catch (Exception e){}
    }
}
