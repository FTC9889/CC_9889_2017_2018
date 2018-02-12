package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 10/29/2017.
 */

public class Intake extends Subsystem{
    private DcMotor rightIntake, leftIntake = null;
    private Servo armRight, armLeft = null;

    private boolean currentLimit = true;

    @Override
    public void outputToTelemetry(Telemetry telemetry) {}

    @Override
    public boolean init(Team9889Linear team9889Linear, boolean auton) {
        try {
            this.rightIntake = team9889Linear.hardwareMap.get(DcMotor.class, Constants.kRightMotorIntakeId);
            this.leftIntake = team9889Linear.hardwareMap.get(DcMotor.class,Constants.kLeftMotorIntakeId);
            this.leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e){
            return false;
        }

        try {
            this.armRight = team9889Linear.hardwareMap.servo.get(Constants.kRightServoIntakeId);
            this.armLeft = team9889Linear.hardwareMap.servo.get(Constants.kLeftServoIntakeId);
            this.armLeft.setDirection(Servo.Direction.REVERSE);
        } catch (Exception e){
            return false;
        }

        if(auton)
            this.stop();
        return true;
    }

    @Override
    public void stop() {
        stopIntake();
        retract();
    }

    @Override
    public void zeroSensors() {}

    public void intake(){
        this.rightIntake.setPower(-1);
        this.leftIntake.setPower(-1);
        deploy();
    }

    public void autoIntake(){
        this.rightIntake.setPower(-0.5);
        this.leftIntake.setPower(-0.5);
        deploy();
    }

    public void twoGlyphSpecial(){
        this.rightIntake.setPower(-1);
        this.leftIntake.setPower(-1);
        this.armLeft.setPosition(0.3);
        this.armRight.setPosition(0.12);
    }

    public void outtake(){
        this.rightIntake.setPower(1);
        this.leftIntake.setPower(1);
    }

    public void stopIntake(){
        this.rightIntake.setPower(0);
        this.leftIntake.setPower(0);
    }

    public void clearArm(){
        this.armLeft.setPosition(0);
        this.armRight.setPosition(0);
    }

    public void deploy(){
        this.armLeft.setPosition(0.12+0.08);
        this.armRight.setPosition(0.16+0.08);
    }

    public void retract(){
        this.armLeft.setPosition(0.6);
        this.armRight.setPosition(0.6);
    }

    public void leftRetract(){
        this.armLeft.setPosition(0.6);
        this.armRight.setPosition(0.12);
        this.rightIntake.setPower(0.5);
        this.leftIntake.setPower(-1);
    }

    public void rightRetract(){
        this.armLeft.setPosition(0.12);
        this.armRight.setPosition(0.6);
        this.rightIntake.setPower(-1);
        this.leftIntake.setPower(0.5);
    }

}
