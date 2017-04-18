package com.team9889.ftc2017.subsystems;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.team9889.ftc2017.Constants;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Drive extends Subsystem {

    private static Drive instance_ = new Drive();

    //Drivetrain Motors
    private DcMotor rightMaster_, rightSlave_, leftMaster_, leftSlave_;

    //Sensors
    private OpticalDistanceSensor BackODS, FrontODS;
    private ModernRoboticsI2cGyro gyro_;
    private UltrasonicSensor ultrasonic;
    private DeviceInterfaceModule CDI;

    //Drive control states
    private DriveControlState driveControlState_ = DriveControlState.POWER;
    private DriveZeroPower driveZeroPower_ = DriveZeroPower.FLOAT;

    public enum DriveZeroPower{
        BRAKE, FLOAT
    }

    public enum DriveControlState{
        POWER, SPEED, POSITION, HEADING_CONTROL, OPERATOR_CONTROL
    }

    public static Drive getInstance(){
        return instance_;
    }

    public void init(HardwareMap hardwareMap, boolean auton){
        rightMaster_ = hardwareMap.dcMotor.get(Constants.kRightDriveMasterId);
        rightSlave_ = hardwareMap.dcMotor.get(Constants.kRightDriveSlaveId);
        leftMaster_ = hardwareMap.dcMotor.get(Constants.kLeftDriveMasterId);
        leftSlave_ = hardwareMap.dcMotor.get(Constants.kLeftDriveSlaveId);

        BackODS = hardwareMap.opticalDistanceSensor.get(Constants.kOpticalDistanceSensor1Id);
        FrontODS = hardwareMap.opticalDistanceSensor.get(Constants.kOpticalDistanceSensor2Id);

        gyro_ = (ModernRoboticsI2cGyro)hardwareMap.get(Constants.kGyroId);

        ultrasonic = hardwareMap.ultrasonicSensor.get(Constants.kLegoUltrasonicSensor1Id);

        CDI = hardwareMap.deviceInterfaceModule.get(Constants.kCoreDeviceInterfaceModule1Id);

        rightMaster_.setDirection(DcMotorSimple.Direction.FORWARD);
        leftMaster_.setDirection(DcMotorSimple.Direction.REVERSE);

        slave();

        DriveControlState(DriveControlState.POWER);
        DriveZeroPowerState(DriveZeroPower.FLOAT);

        zeroSensors();
    }

    public void DriveControlState(DriveControlState state){
        driveControlState_ = state;
        switch (driveControlState_){
            case POWER:
                POWER();
                return;
            case SPEED:
                SPEED();
                return;
            case POSITION:
                POSITION();
                return;
            case HEADING_CONTROL:
                HEADING_CONTROL();
                return;
            case OPERATOR_CONTROL:
                OPERATOR_CONTROL();
                return;
        }
    }

    public void DriveZeroPowerState(DriveZeroPower state){
        driveZeroPower_ = state;
        switch (driveZeroPower_){
            case BRAKE:
                BRAKE();
                return;
            case FLOAT:
                FLOAT();
                return;
        }
    }

    @Override
    public void stop() {
        setLeftRightPower(0,0);
        leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slave();
    }

    @Override
    public void outputToTelemtry(LinearOpMode opMode) {
        opMode.telemetry.addData("Right Motor Pwr", rightMaster_.getPower());
        opMode.telemetry.addData("Left Motor Pwr", leftMaster_.getPower());
        opMode.telemetry.addData("Right Side Inches", getRightDistanceInches());
        opMode.telemetry.addData("Left Side Inches", getLeftDistanceInches());

    }

    public void setLeftRightPower(double left, double right){
        leftMaster_.setPower(left);
        leftSlave_.setPower(left);
        rightMaster_.setPower(right);
        rightSlave_.setPower(left);
    }

    public double getRightDistanceInches(){
        return ticksToInches(rightMaster_.getCurrentPosition());
    }

    public double getLeftDistanceInches(){
        return ticksToInches(leftMaster_.getCurrentPosition());
    }

    private double ticksToInches(int ticks){
        return ticks/Constants.CountsPerInch;
    }

    private int getGyroAngle(){
        return gyro_.getIntegratedZValue();
    }

    public int geGyroHeading(){
        return gyro_.getHeading();
    }

    @Override
    public void zeroSensors() {
        leftMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slave();

        leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slave();

        gyro_.resetZAxisIntegrator();
    }

    public void calibrateGyro(LinearOpMode opMode) throws InterruptedException {
        gyro_.calibrate();

        while (gyro_.isCalibrating()){
            Thread.sleep(50);

            opMode.telemetry.addData("Calibrated", false);
            opMode.telemetry.update();
        }

        opMode.telemetry.addData("Calibrated", true);
        opMode.telemetry.update();
    }

    private void BRAKE(){
        leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slave();
    }

    private void FLOAT(){
        leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        slave();
    }

    private void POWER(){
        leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slave();
    }

    private void SPEED(){
        leftMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slave();
    }

    private void POSITION(){
        leftMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slave();
    }

    private void HEADING_CONTROL(){
        leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        slave();
    }

    private void OPERATOR_CONTROL(){
        leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FLOAT();
        slave();
    }

    private void slave(){
        leftSlave_.setPower(leftMaster_.getPower());
        rightSlave_.setPower(rightMaster_.getPower());
        leftSlave_.setMode(leftMaster_.getMode());
        rightSlave_.setMode(rightMaster_.getMode());
        leftSlave_.setTargetPosition(leftMaster_.getTargetPosition());
        rightSlave_.setTargetPosition(rightMaster_.getTargetPosition());
        leftSlave_.setZeroPowerBehavior(leftMaster_.getZeroPowerBehavior());
        rightSlave_.setZeroPowerBehavior(rightMaster_.getZeroPowerBehavior());
        rightSlave_.setDirection(rightMaster_.getDirection());
        leftSlave_.setDirection(leftMaster_.getDirection());
    }
}
