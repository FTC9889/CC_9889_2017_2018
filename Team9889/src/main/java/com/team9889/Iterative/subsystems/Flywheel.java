package com.team9889.Iterative.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;

/**
 * Created by joshua on 6/21/17.
 */

public class Flywheel implements Subsystem {
    private static Flywheel instance_ = new Flywheel();

    public static Flywheel getInstance(){
        return instance_;
    }

    private DcMotor Flywheel;

    public enum WantedState{
        ON, OFF
    }

    public void init(OpMode opMode, boolean auton){
        try {
            Flywheel = opMode.hardwareMap.dcMotor.get(Constants.kFlywheelMotorId);
            Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            Flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        } catch (Exception e){
            RobotLog.a(Constants.OpMode);
            RobotLog.a("Flywheel motor init error:", e);
        }
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
    public void outputToTelemtry(OpMode opMode) {
        opMode.telemetry.addData("FlywheelPos", Flywheel.getCurrentPosition());
        opMode.telemetry.addData("Flywheel Speed", Flywheel.getPower());
    }

    @Override
    public void stop() {
        WantedState(WantedState.OFF);
    }

    @Override
    public void zeroSensors() {
        Flywheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
