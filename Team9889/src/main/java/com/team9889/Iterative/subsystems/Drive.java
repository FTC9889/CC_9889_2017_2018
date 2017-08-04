package com.team9889.Iterative.subsystems;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsAnalogOpticalDistanceSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.team9889.Constants;
import com.team9889.Iterative.Team9889OpMode;

/**
 * Created by Joshua on 7/29/2017.
 */

public class Drivetrain implements Subsystem{

    //Drive motors
    private DcMotor rightMaster_ , leftMaster_ , rightSlave_ , leftSlave_ = null;

    //Gyro
    private ModernRoboticsI2cGyro gyro_ = null;

    //Light Sensors
    private OpticalDistanceSensor frontOds , backOds = null;



    @Override
    public void init(Team9889OpMode team9889OpMode) {
        this.rightMaster_ = team9889OpMode.hardwareMap.dcMotor.get(Constants.kRightDriveMasterId);
        this.leftMaster_ = team9889OpMode.hardwareMap.dcMotor.get(Constants.kLeftDriveMasterId);
        this.rightSlave_ = team9889OpMode.hardwareMap.dcMotor.get(Constants.kRightDriveSlaveId);
        this.leftSlave_ = team9889OpMode.hardwareMap.dcMotor.get(Constants.kLeftDriveSlaveId);

        this.gyro_ = (ModernRoboticsI2cGyro) team9889OpMode.hardwareMap.gyroSensor.get(Constants.kGyroId);

        this.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void stop(Team9889OpMode team9889OpMode) {
        this.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.setLeftRightPower(0,0);
    }

    @Override
    public void outputTelemetry(Team9889OpMode team9889OpMode){
        team9889OpMode.telemetry.addData("Right Position", this.getRightPosition());
        team9889OpMode.telemetry.addData("Left Position", this.getLeftPosition());
        team9889OpMode.telemetry.addData("Gyro Angle", this.getGyroAngleDegrees());

        team9889OpMode.telemetry.update();
    }

    public void setLeftRightPower(double left, double right){
        this.rightMaster_.setPower(right);
        this.leftMaster_.setPower(left);
        this.rightSlave_.setPower(right);
        this.leftSlave_.setPower(left);
    }

    //Set the Run Mode for the Drive Motors
    private void setRunMode(DcMotor.RunMode runMode){
        this.rightMaster_.setMode(runMode);
        this.leftMaster_.setMode(runMode);
        this.rightSlave_.setMode(runMode);
        this.leftSlave_.setMode(runMode);
    }

    //Set the Zero Power Behavior
    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior zeroPowerBehavior){
        this.rightMaster_.setZeroPowerBehavior(zeroPowerBehavior);
        this.leftMaster_.setZeroPowerBehavior(zeroPowerBehavior);
        this.rightSlave_.setZeroPowerBehavior(zeroPowerBehavior);
        this.leftSlave_.setZeroPowerBehavior(zeroPowerBehavior);
    }

    public int getRightPosition(){
        return rightMaster_.getCurrentPosition();
    }

    public int getLeftPosition(){
        return leftMaster_.getCurrentPosition();
    }

    //Gyro Methods
    public double getGyroAngleDegrees(){
        return gyro_.getIntegratedZValue();
    }

    public double getGyroAngleRadians(){
        return Math.toRadians(this.getGyroAngleDegrees());
    }

}
