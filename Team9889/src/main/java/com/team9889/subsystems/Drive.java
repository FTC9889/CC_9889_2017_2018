package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.team9889.Constants;
import com.team9889.Team9889Linear;
import com.team9889.lib.CruiseLib;
import com.team9889.lib.RevIMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.openftc.hardware.rev.motorStuff.OpenDcMotor;

/**
 * Created by joshua9889 on 10/6/2017.
 */

public class Drive extends Subsystem {

    //Identify variables
    public OpenDcMotor rightMaster_, leftMaster_ = null;

    private RevIMU imu1, imu2 = null;

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
    public boolean init(Team9889Linear team9889Linear, boolean auton) {
        try{
            this.rightMaster_ = team9889Linear.hardwareMap.get(OpenDcMotor.class, Constants.kRightDriveMasterId);
            this.leftMaster_ = team9889Linear.hardwareMap.get(OpenDcMotor.class, Constants.kLeftDriveMasterId);
            this.leftMaster_.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e){
            return false;
        }

        if(auton){
            try {
                this.imu1 = new RevIMU("imu 1", team9889Linear.hardwareMap);
            } catch (Exception e){return false;}


            try {
                this.imu2 = new RevIMU("imu", team9889Linear.hardwareMap);
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
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Left Position", this.getLeftTicks());
        telemetry.addData("Right Position", this.getRightTicks());
        telemetry.addData("Left Power", this.leftMaster_.getPower());
        telemetry.addData("Right Power", this.rightMaster_.getPower());
        telemetry.addData("Left Current", this.leftMaster_.getCurrentDraw().formattedValue);
        telemetry.addData("Right Current", this.rightMaster_.getCurrentDraw().formattedValue);
        telemetry.addData("Gyro Angle", this.getGyroAngleDegrees());
    }

    @Override
    public void zeroSensors() {
        resetEncoders();
    }

    public double getGyroAngleDegrees() {
        try {
            return (imu1.getHeading()+imu2.getHeading())/2;
        } catch (Exception e){
            return 0;
        }

    }

    public double getGyroAngleRadians(){
        return CruiseLib.degreesToRadians(getGyroAngleDegrees());
    }

    public double getLeftDistanceInches(){
        return Constants.ticks2Inches(getLeftTicks());
    }

    public int getLeftTicks() {
        return this.leftMaster_.getCurrentPosition();
    }

    public double getRightDistanceInches(){
        return Constants.ticks2Inches(getRightTicks());
    }

    public int getRightTicks(){
        return this.rightMaster_.getCurrentPosition();
    }

    /**
     * @return Returns the current Left velocity , in Radians per second
     */
    public double getLeftVelocity(){
        return this.leftMaster_.getVelocity(AngleUnit.RADIANS);
    }

    /**
     * @return Returns the current Right velocity , in Radians per second
     */
    public double getRightVelocity(){
        return this.rightMaster_.getVelocity(AngleUnit.RADIANS);
    }

    /**
     * @param left Wanted Left Power between [-1.0,1.0]
     * @param right Wanted Right Power between [-1.0,1.0]
     */
    public void setLeftRightPower(double left, double right) {
        try {
            this.leftMaster_.setPower(CruiseLib.limitValue(left, 1, -1));
            this.rightMaster_.setPower(CruiseLib.limitValue(right, 1, -1));
        } catch (Exception e){}

    }

    public void SpeedTurn(double speed, double turn){
        double left = speed + turn;
        double right = speed - turn;
        setVelocityTarget(left, right);
    }

    /**
     * @param left Wanted Left Velocity, in Radians per second
     * @param right Wanted RIght Velocity, in Radians per second
     */
    public void setVelocityTarget(double left, double right) {
        this.leftMaster_.setVelocity(left, AngleUnit.RADIANS);
        this.rightMaster_.setVelocity(right, AngleUnit.RADIANS);
    }

    public void setLeftRightPath(int left_pos, int right_pos, double left_power, double right_power){
        isFinishedRunningToPosition = false;
        this.DriveControlState(DriveControlStates.POSITION);
        this.DriveZeroPowerState(DriveZeroPowerStates.BRAKE);

        this.leftMaster_.setTargetPosition(left_pos);
        this.rightMaster_.setTargetPosition(right_pos);
        this.setLeftRightPower(left_power, right_power);

        if(Math.abs(leftMaster_.getTargetPosition()) - Math.abs(leftMaster_.getCurrentPosition()) < 20
                && Math.abs(rightMaster_.getTargetPosition())- Math.abs(rightMaster_.getCurrentPosition()) < 20) {
            this.setLeftRightPower(0,0);
            isFinishedRunningToPosition = true;
        }
    }

    public void setTargetTolerence(int tolerance) {
        try {
            this.leftMaster_.setTargetPositionTolerance(tolerance);
            this.rightMaster_.setTargetPositionTolerance(tolerance);
        } catch (Exception e){}
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

    private void BRAKE(){
        try {
            this.leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            this.rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e){}
    }

    private void FLOAT(){
        try {
            this.leftMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            this.rightMaster_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } catch (Exception e){}
    }

    private void withoutEncoders(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e){}
    }

    private void withEncoders(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } catch (Exception e){}
    }

    private void runToPosition(){
        try {
            this.leftMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            this.rightMaster_.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        } catch (Exception e){}
    }

    //Reset encoders until They both equal 0
    public void resetEncoders() {
        try {
            while(getLeftTicks() != 0 && getRightTicks() != 0){
                leftMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                rightMaster_.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
        } catch (Exception e) {}
    }
}
