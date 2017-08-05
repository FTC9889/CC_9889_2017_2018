package com.team9889.Linear.subsystems;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Flywheel extends Subsystem{
    private DcMotor Flywheel;

    public enum WantedState{
        ON, OFF
    }

    @Override
    public boolean init(HardwareMap hardwareMap, boolean auton){
        boolean error = false;
        try {
            Flywheel = hardwareMap.dcMotor.get(Constants.kFlywheelMotorId);
            Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } catch (Exception e){
            error = true;
        }

        this.WantedState(WantedState.OFF);
        this.zeroSensors();

        return !error;
    }

    public void WantedState(WantedState wantedState){
        switch (wantedState){
            case ON:
                Shoot();
                return;
            case OFF:
                Off();
                return;
        }
    }

    private void Shoot(){
        Flywheel.setPower(-0.3);
    }

    private void Off(){
        Flywheel.setPower(0.0);
    }

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("FlywheelPos", Flywheel.getCurrentPosition());
        opMode.telemetry.addData("Flywheel Speed", Flywheel.getPower());
    }

    @Override
    public void stop() {
        Off();
    }

    @Override
    public void zeroSensors() {
        Flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
