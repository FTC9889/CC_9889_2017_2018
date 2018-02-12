package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 10/29/2017.
 */

public class Jewel extends Subsystem {

    private Servo arm, wrist;

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Jewel Arm Pos", this.arm.getPosition());
        telemetry.addData("Jewel Wrist Pos", this.wrist.getPosition());
    }

    @Override
    public boolean init(Team9889Linear team9889Linear, boolean auton) {

        try {
            this.arm = team9889Linear.hardwareMap.servo.get(Constants.kJewelArmId);
        } catch (Exception e) {
            return false;
        }

        try {
            this.wrist = team9889Linear.hardwareMap.servo.get(Constants.kJewelWristId);
        } catch (Exception e) {
            return false;
        }

        if(auton){
            this.stop();
        }

        return true;
    }

    @Override
    public void stop() {
        this.retract();
        this.right();
    }

    @Override
    public void zeroSensors() {}

    public void retract() {
        try {
            this.arm.setPosition(Constants.RetractedJewelArm);
            this.wrist.setPosition(Constants.CenterJewelWrist);
        } catch (Exception e) {}
    }

    public void deploy() {
        try {
            this.arm.setPosition(Constants.DeployedJewelArm);
            wrist.setPosition(Constants.CenterJewelWrist);
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

    public void outtake(){
        try {
            this.wrist.setPosition(Constants.CenterJewelWrist/2);
        } catch (Exception e){}
    }
}
