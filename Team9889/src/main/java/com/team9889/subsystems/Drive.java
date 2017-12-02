package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.CruiseLib;
import com.team9889.lib.RevIMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

/**
 * Created by joshua9889 on 10/6/2017.
 */

public class Drive extends Subsystem {

    //Identify variables
    public DcMotorEx rightMaster_, leftMaster_ = null;

    private RevIMU imu1, imu2 = null;

    private PIDCoefficients lPID, rPID;

    private boolean isFinishedRunningToPosition = false;

    public enum DriveZeroPowerStates {
        BRAKE,
        FLOAT
    }

    public enum DriveControlStates {
        POWER,
        SPEED,
        POSITION,
        OPERATOR_CONTROL
    }

    @Override //This is KINDA like the hardwareMap, but then again I'm not too sure.
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try{
            this.rightMaster_ = (DcMotorEx)team9889LinearOpMode.hardwareMap.get(DcMotor.class, Constants.kRightDriveMasterId);
            this.leftMaster_ = (DcMotorEx)team9889LinearOpMode.hardwareMap.get(DcMotor.class, Constants.kLeftDriveMasterId);
        } catch (Exception e){
            return false;
        }

        if(auton){
            try {
                this.imu1 = new RevIMU("imu 1", team9889LinearOpMode.hardwareMap);
            } catch (Exception e){return false;}


            try {
                this.imu2 = new RevIMU("imu", team9889LinearOpMode.hardwareMap);
            } catch (Exception e){return false;}
        }

        return true;
    }

    @Override
    public void stop() {
        this.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);
        this.setLeftRightPower(0,0);
    }

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Left Position", -this.leftMaster_.getCurrentPosition());
        opMode.telemetry.addData("Right Position", this.rightMaster_.getCurrentPosition());
        opMode.telemetry.addData("Left Power", this.leftMaster_.getPower());
        opMode.telemetry.addData("Right Power", this.rightMaster_.getPower());
        opMode.telemetry.addData("Gyro Angle", this.getGyroAngleDegrees());
    }

    @Override
    public void zeroSensors() {
        resetEncoders();
    }

    public double getGyroAngleDegrees() {
        try {
            return (imu1.getHeading());//+imu2.getHeading())/2;
        } catch (Exception e){
            return 0;
        }

    }

    public double getLeftDistanceInches(){
        return Constants.ticks2Inches(getLeftTicks());
    }

    public int getLeftTicks() {
        return -this.leftMaster_.getCurrentPosition();
    }

    public double getRightDistanceInches(){
        return Constants.ticks2Inches(getRightTicks());
    }

    public int getRightTicks(){
        return this.rightMaster_.getCurrentPosition();
    }

    public void setLeftRightPower(double left, double right) {
        try {
            this.rightMaster_.setPower(-left);
            this.leftMaster_.setPower(right);
        } catch (Exception e){}

    }

    public void setLeftRightPath(int left_pos, int right_pos, double left_power, double right_power){
        isFinishedRunningToPosition = false;
        this.DriveControlState(DriveControlStates.POSITION);
        this.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);

        this.leftMaster_.setTargetPosition(left_pos);
        this.rightMaster_.setTargetPosition(-right_pos);
        this.setLeftRightPower(left_power, right_power);

        if(Math.abs(leftMaster_.getTargetPosition()) - Math.abs(leftMaster_.getCurrentPosition()) < 20
                && Math.abs(rightMaster_.getTargetPosition())- Math.abs(rightMaster_.getCurrentPosition()) < 20) {
            this.setLeftRightPower(0,0);
            isFinishedRunningToPosition = true;
        }
    }

    public boolean isFinishedRunningToPosition(){
        return !isFinishedRunningToPosition;
    }

    public void DriveControlState(DriveControlStates state){
        switch (state){
            case POWER:
                this.withoutEncoders();
                break;
            case SPEED:
                this.withEncoders();
                break;
            case POSITION:
                this.runToPosition();
                break;
            case OPERATOR_CONTROL:
                this.withoutEncoders();
                break;
        }
    }

    public void DriveZeroPowerState(DriveZeroPowerStates state){
        switch (state){
            case BRAKE:
                this.BRAKE();
                break;
            case FLOAT:
                this.FLOAT();
                break;
        }
    }

    public void setPIDConstants(double lP, double lI, double lD, double rP, double rI, double rD){
        lPID = new PIDCoefficients(lP, lI, lD);
        rPID = new PIDCoefficients(rP, rI, rD);

        try {
            leftMaster_.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, lPID);
            rightMaster_.setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, rPID);
        } catch (Exception e){}
    }

    private void BRAKE(){
        try {
            this.leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //this.slave();
        } catch (Exception e){}
    }

    private void FLOAT(){
        try {
            this.leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            //this.slave();
        } catch (Exception e){}
    }

    private void withoutEncoders(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //this.slave();
        } catch (Exception e){}
    }

    private void withEncoders(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //this.slave();
        } catch (Exception e){}
    }

    private void runToPosition(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //this.slave();
        } catch (Exception e){}
    }

    //Reset encoders and remember the previous RunMode
    public void resetEncoders() {
        try {
            leftMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        } catch (Exception e) {}
    }

    public void setTargetTolerence(int tolerance) {
        try {
            this.leftMaster_.setTargetPositionTolerance(tolerance);
            this.rightMaster_.setTargetPositionTolerance(tolerance);
        } catch (Exception e){}
    }

    public void setVelocityTarget() {
        this.rightMaster_.setVelocity(960, AngleUnit.DEGREES);
        this.leftMaster_.setVelocity(960, AngleUnit.DEGREES);
    }

}
