package com.team9889.ftc2017.subsystems;

import android.content.ClipData;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Util;
import com.team9889.ftc2017.Constants;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Flywheel extends Subsystem{

    private static Flywheel instance_ = new Flywheel();

    public static Flywheel getInstance(){
        return instance_;
    }

    private DcMotor Flywheel;
    private int CurrentValue = 0;
    private int LastValue = 0;
    private int RPM;

    public enum WantedState{
        ON, OFF
    }

    public void init(HardwareMap hardwareMap, boolean auton){
        Flywheel = hardwareMap.dcMotor.get(Constants.kFlywheelMotorId);
        Flywheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Flywheel.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        Off();

        zeroSensors();
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
    public void outputToTelemtry(LinearOpMode opMode) {
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
