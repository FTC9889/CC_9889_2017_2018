package com.team9889.Iterative.subsystems;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;

import org.simbotics.robot.util.SimLib;

import static com.team9889.Constants.ticksToInches;

/**
 * Created by joshua on 6/21/17.
 *
 * Class for Drivetrain
 */

public class Drive implements Subsystem{
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

    @Override
    public void init(OpMode opMode, boolean auton){
        try {
            this.rightMaster_ = opMode.hardwareMap.dcMotor.get(Constants.kRightDriveMasterId);
            this.rightSlave_ = opMode.hardwareMap.dcMotor.get(Constants.kRightDriveSlaveId);
            this.leftMaster_ = opMode.hardwareMap.dcMotor.get(Constants.kLeftDriveMasterId);
            this.leftSlave_ = opMode.hardwareMap.dcMotor.get(Constants.kLeftDriveSlaveId);

            this.rightMaster_.setDirection(DcMotorSimple.Direction.FORWARD);
            this.leftMaster_.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e){
            RobotLog.a(Constants.OpMode);
            RobotLog.a("drivetrain motor init error:", e);
        }

        try {
            this.BackODS = opMode.hardwareMap.opticalDistanceSensor.get(Constants.kOpticalDistanceSensor1Id);
            this.FrontODS = opMode.hardwareMap.opticalDistanceSensor.get(Constants.kOpticalDistanceSensor2Id);
        } catch (Exception e){
            RobotLog.a(Constants.OpMode);
            RobotLog.a("drivetrain ODS init error:", e);
        }

        try {
            this.gyro_ = (ModernRoboticsI2cGyro)opMode.hardwareMap.get(Constants.kGyroId);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("drivetrain GYRO init error", e);
        }

        try {
            this.ultrasonic = opMode.hardwareMap.ultrasonicSensor.get(Constants.kLegoUltrasonicSensor1Id);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("drivetrain ULTRA init error", e);
        }

        try {
            this.CDI = opMode.hardwareMap.deviceInterfaceModule.get(Constants.kCoreDeviceInterfaceModule1Id);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("drivetrain CDI init error", e);
        }

        slave();
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
    public void outputToTelemtry(OpMode opMode) {
        opMode.telemetry.addData("Right Motor Pwr", rightMaster_.getPower());
        opMode.telemetry.addData("Left Motor Pwr", leftMaster_.getPower());
        opMode.telemetry.addData("Right Side Inches", getRightDistanceInches());
        opMode.telemetry.addData("Left Side Inches", getLeftDistanceInches());
        opMode.telemetry.addData("Right side ticks", rightMaster_.getCurrentPosition());
        opMode.telemetry.addData("Left side ticks", leftMaster_.getCurrentPosition());
        opMode.telemetry.addData("Front ODS Raw", FrontODS.getRawLightDetected());
        opMode.telemetry.addData("Back ODS Raw", BackODS.getRawLightDetected());
        opMode.telemetry.addData("Gyro Angle", getGyroAngleDegrees());
        opMode.telemetry.addData("Ultrasonic", getUltrasonic());
    }

    public void setLeftRightPower(double left, double right){
        leftMaster_.setPower(SimLib.limitValue(left));
        leftSlave_.setPower(SimLib.limitValue(left));
        rightMaster_.setPower(SimLib.limitValue(right));
        rightSlave_.setPower(SimLib.limitValue(right));
    }

    public double getRightDistanceInches(){
        return ticksToInches(rightMaster_.getCurrentPosition());
    }

    public double getLeftDistanceInches(){
        return ticksToInches(leftMaster_.getCurrentPosition());
    }

    public int getGyroAngleDegrees(){
        return gyro_.getIntegratedZValue();
    }

    public double getGyroAngleRadians(){
        return Math.toRadians(getGyroAngleDegrees());
    }

    public int getGyroHeading(){
        return gyro_.getHeading();
    }

    public double getUltrasonic(){
        return ultrasonic.getUltrasonicLevel();
    }

    public boolean getWhiteLine(OpticalDistanceSensor opticalDistanceSensor){
        return opticalDistanceSensor.getRawLightDetected() > Constants.WhiteLineValue;
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

    public void calibrateGyro(OpMode opMode) throws InterruptedException {
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
