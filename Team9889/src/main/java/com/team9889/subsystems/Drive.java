package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.RevIMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.security.spec.ECField;

/**
 * Created by joshua9889 on 10/6/2017.
 */

public class Drive extends Subsystem {

    private DcMotorEx rightMaster_, leftMaster_ = null;

    private RevIMU imu = null;

    private PIDCoefficients lPID, rPID;

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try{
            this.rightMaster_ = (DcMotorEx)team9889LinearOpMode.InternalopMode.hardwareMap.
                    dcMotor.get(Constants.kRightDriveMasterId);
            this.leftMaster_ = (DcMotorEx)team9889LinearOpMode.InternalopMode.hardwareMap.
                    dcMotor.get(Constants.kLeftDriveMasterId);
        } catch (Exception e){
            return false;
        }

        try {
            this.imu = new RevIMU(team9889LinearOpMode.InternalopMode, Constants.kIMUId, "imu.json");
        } catch (Exception e){
            return false;
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
        opMode.telemetry.addData("Left Position", this.leftMaster_.getCurrentPosition());
        opMode.telemetry.addData("Right Position", this.rightMaster_.getCurrentPosition());
        opMode.telemetry.addData("Left Power", this.leftMaster_.getPower());
        opMode.telemetry.addData("Right Power", this.rightMaster_.getPower());
    }

    @Override
    public void zeroSensors() {
        resetEncoders();
    }

    public void setLeftRightPower(double left, double right) {
        try {
            this.rightMaster_.setPower(left);
            this.leftMaster_.setPower(right);
        } catch (Exception e){}

    }

    public void setLeftRightPath(int left_pos, int right_pos, double left_power, double right_power){
        this.DriveControlState(DriveControlStates.POSITION);
        this.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);

        this.leftMaster_.setTargetPosition(left_pos);
        this.rightMaster_.setTargetPosition(right_pos);
        this.setLeftRightPower(left_power, right_power);
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
            DcMotor.RunMode leftRunMode = leftMaster_.getMode();
            DcMotor.RunMode rightRunMode = rightMaster_.getMode();
            leftMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftMaster_.setMode(leftRunMode);
            rightMaster_.setMode(rightRunMode);
        } catch (Exception e) {}
    }

    public void setTargetTolerence(int tolerence) {
        try {
            this.leftMaster_.setTargetPositionTolerance(tolerence);
            this.rightMaster_.setTargetPositionTolerance(tolerence);
        } catch (Exception e){}
    }

    public void setVelocityTarget() {
        this.rightMaster_.setVelocity(960, AngleUnit.DEGREES);
        this.leftMaster_.setVelocity(960, AngleUnit.DEGREES);
    }

}
