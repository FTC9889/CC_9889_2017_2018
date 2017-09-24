package com.team9889.subsystems;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

import static com.team9889.Constants.ticksToInches;
import static com.team9889.lib.CruiseLib.degreesToRadians;
import static com.team9889.lib.CruiseLib.limitValue;

/**
 * Created by joshua9889 on 4/10/2017.
 */

public class Drive extends Subsystem {

    //Drive Motors
    private DcMotor rightMaster_, rightSlave_, leftMaster_, leftSlave_;

    //Sensors
    private ModernRoboticsI2cGyro gyro_;
    private DeviceInterfaceModule CDI;

    //Drive control states
    private DriveControlState driveControlState_ = DriveControlState.POWER;
    private DriveZeroPower driveZeroPower_ = DriveZeroPower.FLOAT;

    public enum DriveZeroPower{
        BRAKE, FLOAT
    }

    public enum DriveControlState{
        POWER, SPEED, POSITION, OPERATOR_CONTROL
    }

    public boolean init(HardwareMap hardwareMap, boolean auton){
        try {
            this.rightMaster_ = hardwareMap.dcMotor.get(Constants.kRightDriveMasterId);
            this.rightSlave_ = hardwareMap.dcMotor.get(Constants.kRightDriveSlaveId);
            this.leftMaster_ = hardwareMap.dcMotor.get(Constants.kLeftDriveMasterId);
            this.leftSlave_ = hardwareMap.dcMotor.get(Constants.kLeftDriveSlaveId);

            this.rightMaster_.setDirection(DcMotorSimple.Direction.FORWARD);
            this.leftMaster_.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e){
            return false;
        }

        try {
            this.gyro_ = (ModernRoboticsI2cGyro)hardwareMap.get(Constants.kGyroId);
        } catch (Exception e) {
            return false;
        }

        try {
            this.CDI = hardwareMap.deviceInterfaceModule.get(Constants.kCoreDeviceInterfaceModule1Id);
        } catch (Exception e) {
            return false;
        }

        this.slave();

        this.zeroSensors();
        this.DriveControlState(Drive.DriveControlState.POWER);
        this.DriveZeroPowerState(Drive.DriveZeroPower.FLOAT);
        return true;
    }

    public void DriveControlState(DriveControlState state){
        this.driveControlState_ = state;
        switch (driveControlState_){
            case POWER:
                this.POWER();
                break;
            case SPEED:
                this.SPEED();
                break;
            case POSITION:
                this.POSITION();
                break;
            case OPERATOR_CONTROL:
                this.OPERATOR_CONTROL();
                break;
        }
    }

    public void DriveZeroPowerState(DriveZeroPower state){
        this.driveZeroPower_ = state;
        switch (driveZeroPower_){
            case BRAKE:
                this.BRAKE();
                break;
            case FLOAT:
                this.FLOAT();
                break;
        }
    }

    @Override
    public void stop() {
        this.setLeftRightPower(0,0);
        this.DriveZeroPowerState(DriveZeroPower.FLOAT);
    }

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Right Motor Pwr", this.rightMaster_.getPower());
        opMode.telemetry.addData("Left Motor Pwr", this.leftMaster_.getPower());
        opMode.telemetry.addData("Right Side Inches", this.getRightDistanceInches());
        opMode.telemetry.addData("Left Side Inches", this.getLeftDistanceInches());
        opMode.telemetry.addData("Right side ticks", this.rightMaster_.getCurrentPosition());
        opMode.telemetry.addData("Left side ticks", this.leftMaster_.getCurrentPosition());
        opMode.telemetry.addData("Gyro Angle", this.getGyroAngleDegrees());
    }

    public void setLeftRightPower(double left, double right){
        try{
            this.leftMaster_.setPower(limitValue(left));
            this.leftSlave_.setPower(limitValue(left));
            this.rightMaster_.setPower(limitValue(right));
            this.rightSlave_.setPower(limitValue(right));
        }catch (Exception e){}
    }

    public double getRightDistanceInches(){
        return ticksToInches(this.rightMaster_.getCurrentPosition());
    }

    public double getLeftDistanceInches(){
        try{
            return ticksToInches(this.leftMaster_.getCurrentPosition());
        } catch (Exception e) {
            return 0.0;
        }
    }

    public boolean InchesAreWeThereYet(double inches){
        return !(Math.abs(this.getRightDistanceInches()) > Math.abs(inches));
    }

    public int getGyroAngleDegrees(){
        try {
            return this.gyro_.getIntegratedZValue();
        } catch (Exception e){
            return 0;
        }
    }

    public double getGyroAngleRadians(){
        return degreesToRadians(this.getGyroAngleDegrees());
    }

    public int getGyroHeading(){
        try {
            return this.gyro_.getHeading();
        } catch (Exception e){
            return 0;
        }
    }

    @Override
    public void zeroSensors() {
        this.zeroDriveMotors();
        this.gyro_.resetZAxisIntegrator();
    }

    public void zeroDriveMotors(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            this.slave();

            this.leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.slave();
        }catch (Exception e){}
    }

    public void calibrateGyro(LinearOpMode opMode) throws InterruptedException {
        try {
            this.gyro_.calibrate();

            while (this.gyro_.isCalibrating()){
                Thread.sleep(50);

                opMode.telemetry.addData("Calibrated", false);
                opMode.telemetry.update();
            }

            opMode.telemetry.addData("Calibrated", true);
            opMode.telemetry.update();
        } catch (Exception e){}
    }

    private void BRAKE(){
        try {
            this.leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.slave();
        } catch (Exception e){}
    }

    private void FLOAT(){
        try {
            this.leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.slave();
        } catch (Exception e){}
    }

    private void POWER(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.slave();
        } catch (Exception e){}
    }

    private void SPEED(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.slave();
        } catch (Exception e){}
    }

    private void POSITION(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.slave();
        } catch (Exception e){}
    }

    private void OPERATOR_CONTROL(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.FLOAT();
            this.slave();
        } catch (Exception e){}
    }

    private void slave(){
        try {
            this.leftSlave_.setPower(this.leftMaster_.getPower());
            this.rightSlave_.setPower(this.rightMaster_.getPower());
            this.leftSlave_.setMode(this.leftMaster_.getMode());
            this.rightSlave_.setMode(this.rightMaster_.getMode());
            this.leftSlave_.setTargetPosition(this.leftMaster_.getTargetPosition());
            this.rightSlave_.setTargetPosition(this.rightMaster_.getTargetPosition());
            this.leftSlave_.setZeroPowerBehavior(this.leftMaster_.getZeroPowerBehavior());
            this.rightSlave_.setZeroPowerBehavior(this.rightMaster_.getZeroPowerBehavior());
            this.rightSlave_.setDirection(this.rightMaster_.getDirection());
            this.leftSlave_.setDirection(this.leftMaster_.getDirection());
        } catch (Exception e){}
    }

}
