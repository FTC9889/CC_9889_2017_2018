package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 10/29/2017.
 */

public class Intake extends Subsystem{
    private DcMotor rightIntake, leftIntake = null;
    private CRServo armRight, armLeft = null;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Intake right", rightIntake.getPower());
        //opMode.telemetry.addData("Intake left", leftIntake.getPower());
//        opMode.telemetry.addData("Intake FL", armRight.getPower());
//        opMode.telemetry.addData("Intake BL", armLeft.getPower());
    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try {
            this.rightIntake = team9889LinearOpMode.hardwareMap.dcMotor.get(Constants.kRightIntakeId);
            this.leftIntake = team9889LinearOpMode.hardwareMap.dcMotor.get(Constants.kLeftIntakeId);
            this.armRight = team9889LinearOpMode.hardwareMap.crservo.get(Constants.kRightArmId);
            this.armLeft = team9889LinearOpMode.hardwareMap.crservo.get(Constants.kLeftArmId);
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
            rightIntake.setPower(power);
            leftIntake.setPower(-power);
            armRight.setPower(power);
            armLeft.setPower(-power);
        } catch (Exception e){}
    }

    public void intakeOne(double power){
        try {
            rightIntake.setPower(power);
            leftIntake.setPower(-power);
            armRight.setPower(power);
            armLeft.setPower(-power);
        } catch (Exception e){}
    }

    public void outtake(double power){
        intakeTwo(1);
    }

    public void waitToScore(){
        intakeTwo(0.0);
    }

    /*-------------------------------------
    public void intakeArm(double power){
        armRight.setPower(power);
        armLeft.setPower(-power);
    }
    -------------------------------------*/

    public void intake(double power){
        rightIntake.setPower(power);
        leftIntake.setPower(-power);
        armRight.setPower(power);
        armLeft.setPower(-power);
    }
}
