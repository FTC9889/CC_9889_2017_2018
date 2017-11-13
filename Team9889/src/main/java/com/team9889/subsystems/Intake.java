package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 10/29/2017.
 */

public class Intake extends Subsystem{
    private CRServo frontRight, backRight, frontLeft, backLeft = null;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Intake FR", frontRight.getPower());
        //opMode.telemetry.addData("Intake BR", backRight.getPower());
//        opMode.telemetry.addData("Intake FL", frontLeft.getPower());
//        opMode.telemetry.addData("Intake BL", backLeft.getPower());
    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try {
            this.frontRight = team9889LinearOpMode.hardwareMap.crservo.get(Constants.kRightFrontIntakeId);
            this.backRight = team9889LinearOpMode.hardwareMap.crservo.get(Constants.kRightBackIntakeId);
            this.frontLeft = team9889LinearOpMode.hardwareMap.crservo.get(Constants.kLeftFrontIntakeId);
            this.backLeft = team9889LinearOpMode.hardwareMap.crservo.get(Constants.kLeftBackIntakeId);
        } catch (Exception e){
            return false;
        }

        this.stop();

        return true;
    }

    @Override
    public void stop() {
        intakeTwo(0);
    }

    @Override
    public void zeroSensors() {}

    public void intakeTwo(double power){
        try {
            frontRight.setPower(power);
            backRight.setPower(power);
            frontLeft.setPower(-power);
            backLeft.setPower(-power);
        } catch (Exception e){}
    }

    public void intakeOne(double power){
        try {
            frontRight.setPower(power);
            backRight.setPower(0.1);
            frontLeft.setPower(-power);
            backLeft.setPower(-0.1);
        } catch (Exception e){}
    }

    public void outtake(double power){
        intakeTwo(1);
    }

    public void waitToScore(){
        intakeTwo(0.0);
    }

    public void intakeLeft(double power){
        frontLeft.setPower(-power);
        backLeft.setPower(-power);
    }

    public void intakeRight(double power){
        frontRight.setPower(power);
        backRight.setPower(power);
    }
}
