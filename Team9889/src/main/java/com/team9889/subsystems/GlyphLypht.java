package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889Linear;

/**
 * Created by Jin on 11/3/2017.
 */

public class GlyphLypht extends Subsystem{
    public enum Mode {
        Auto, Teleop, Intake, Level2, Level4
    }

    private DcMotor RightLift, LeftLift = null;
    private Servo RightServo, LeftServo = null;
    private Servo RightFinger, LeftFinger = null;

    @Override
    public void outputToTelemetry(Team9889Linear opMode) {
        opMode.telemetry.addData("Left Lift Pos", LeftLift.getCurrentPosition());
        opMode.telemetry.addData("Right Lift Pos", RightLift.getCurrentPosition());
    }

    @Override
    public boolean init(Team9889Linear team9889Linear, boolean auton) {
        try{
            this.RightLift = team9889Linear.hardwareMap.dcMotor.get(Constants.kLeftGlyphLift);
            this.LeftLift = team9889Linear.hardwareMap.dcMotor.get(Constants.kRightGlyphLift);
            this.stop();
        } catch (Exception e){
            return false;
        }

        try {
            this.RightServo = team9889Linear.hardwareMap.servo.get(Constants.kRightGlyphServoId);
            this.LeftServo = team9889Linear.hardwareMap.servo.get(Constants.kLeftGlyphServoId);
            this.LeftServo.setDirection(Servo.Direction.REVERSE);
        } catch (Exception e){
            return false;
        }

        try {
            this.RightFinger = team9889Linear.hardwareMap.servo.get(Constants.kRightFingerId);
            this.LeftFinger = team9889Linear.hardwareMap.servo.get(Constants.kLeftFingerId);
            this.LeftFinger.setDirection(Servo.Direction.REVERSE);
        } catch (Exception e){
            return false;
        }

        if(auton)
            this.goTo(Mode.Auto);
        else
            this.goTo(Mode.Teleop);
        this.zeroSensors();
        this.stop();

        return true;
    }

    @Override
    public void stop() {
        this.RightLift.setPower(0);
        this.LeftLift.setPower(0);
    }

    @Override
    public void zeroSensors() {
        this.RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void clamp(){
        setFingerPosition(0.3);
    }

    public void release(){
        setFingerPosition(0.0);
    }

    private void setFingerPosition(double position){
        RightFinger.setPosition(position);
        LeftFinger.setPosition(position);
    }

    public void setLiftPosition(int position, double power){
        this.setLiftPosition(position, position, power);
    }

    public void setLiftPosition(int leftPosition, int rightPosition, double power){
        this.RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        RightLift.setTargetPosition(rightPosition);
        LeftLift.setTargetPosition(-leftPosition);

        this.RightLift.setPower(power);
        this.LeftLift.setPower(-power);
    }

    public void setServoPosition(double position){
        this.RightServo.setPosition(position);
        this.LeftServo.setPosition(position);
    }

    /**
     * @param level What row level to goto
     */
    public void goTo(Mode level) {
        switch (level){
            case Intake:
                setServoPosition(0.37);
                setLiftPosition(Constants.GLintake, Constants.maxSpeed);
                release();
                break;
            case Level2:
                setServoPosition(0.1);
                setLiftPosition(Constants.GLsecond, Constants.GLsecond, Constants.maxSpeed);
                clamp();
                break;
            case Level4:
                setServoPosition(0.5);
                setLiftPosition(Constants.GLtop, Constants.GLtop, Constants.maxSpeed-0.1);
                clamp();
                break;
            case Auto:
                setServoPosition(0.0);
                setLiftPosition(0, 0);
                clamp();
                break;
            case Teleop:
                setServoPosition(0.37);
                setLiftPosition(0, 0);
                release();
                break;
        }
    }
}
