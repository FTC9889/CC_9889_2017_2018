package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by Jin on 11/3/2017.
 */

public class GlyphLypht extends Subsystem{

    public int[] leftModPositions = {
            0, 0, 0, 0, 0
    };

    public int[] rightModPositions = {
            0, 0, 0, 0, 0
    };

    public enum Mode {
        Intake, Level1, Level2, Level3, Level4
    }

    private DcMotor RightLift, LeftLift = null;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Left Lift Pos", LeftLift.getCurrentPosition());
        opMode.telemetry.addData("Right Lift Pos", RightLift.getCurrentPosition());
    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try{
            this.RightLift = team9889LinearOpMode.hardwareMap.dcMotor.get(Constants.kLeftGlyphLift);
            this.LeftLift = team9889LinearOpMode.hardwareMap.dcMotor.get(Constants.kRightGlyphLift);
            this.stop();
        } catch (Exception e){
            return false;
        }

        this.zeroSensors();
        this.stop();
        this.setPosition(0,0.2);

        return true;
    }

    @Override
    public void stop() {
        this.setPower(0.0);
    }

    @Override
    public void zeroSensors() {
        this.RightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.LeftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void setPower(double power){
        this.RightLift.setPower(power);
        this.LeftLift.setPower(-power);
    }

    public void setPosition(int position, double power){
        this.setPosition(position, position, power);
    }

    public void setPosition(int leftPosition, int rightPosition, double power){
        this.RightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.LeftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        RightLift.setTargetPosition(rightPosition);
        LeftLift.setTargetPosition(-leftPosition);

        setPower(power);
    }

    public void goTo(Mode level) {
        switch (level){
            case Intake:
                setPosition(Constants.GLintake + leftModPositions[0], Constants.GLintake + rightModPositions[0], Constants.maxSpeed);
                break;
            case Level1:
                setPosition(Constants.GLbottom + leftModPositions[1], Constants.GLbottom + rightModPositions[1], Constants.maxSpeed);
                break;
            case Level2:
                setPosition(Constants.GLsecond + leftModPositions[2], Constants.GLsecond + rightModPositions[2], Constants.maxSpeed);
                break;
            case Level3:
                setPosition(Constants.GLthird + leftModPositions[3], Constants.GLthird + rightModPositions[3], Constants.maxSpeed);
                break;
            case Level4:
                setPosition(Constants.GLtop + leftModPositions[4], Constants.GLtop + rightModPositions[4], Constants.maxSpeed);
                break;
        }
    }
}
