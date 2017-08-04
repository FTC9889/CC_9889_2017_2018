package com.team9889.Linear.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Intake extends Subsystem {
    private static Intake instance_ = new Intake();

    public static Intake getInstance(){
        return instance_;
    }

    private DcMotor mIntakeMotor;
    private CRServo mIntakeServo;

    public void init(HardwareMap hardwareMap, boolean auton){
        try {
            mIntakeMotor = hardwareMap.dcMotor.get(Constants.kIntakeMotorId);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("Intake DC Init Error");
        }

        try {
            mIntakeServo = hardwareMap.crservo.get(Constants.kIntakeServo);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("Intake Servo Init Error");
        }
    }

    public enum WantedState {
        WANTS_STOP, WANTS_INTAKE, WANTS_REVERSE, WANTS_SHOOT, WANTS_WAIT
    }

    private WantedState mWantedState = WantedState.WANTS_STOP;

    private void start(){
        WantedState(WantedState.WANTS_STOP);
    }

    public void WantedState(WantedState wantedState) {
        mWantedState = wantedState;
        switch (mWantedState){
            case WANTS_INTAKE:
                mIntakeMotor.setPower(Constants.kIntakeMotorSpeedIntake);
                mIntakeServo.setPower(Constants.kIntakeServoSpeedIntake);
                return;
            case WANTS_REVERSE:
                mIntakeMotor.setPower(Constants.kIntakeMotorSpeedOuttake);
                mIntakeServo.setPower(Constants.kIntakeServoSpeedOuttake);
                return;
            case WANTS_SHOOT:
                mIntakeMotor.setPower(Constants.kIntakeMotorSpeedShoot);
                mIntakeServo.setPower(Constants.kIntakeServoSpeedShoot);
                return;
            case WANTS_STOP:
                mIntakeMotor.setPower(0);
                mIntakeServo.setPower(0);
                return;
            case WANTS_WAIT:
                mIntakeMotor.setPower(Constants.kIntakeMotorSpeedWait);
                mIntakeServo.setPower(Constants.kIntakeServoSpeedWait);
                return;
            default:
                stop();
                break;
            }
    }

    @Override
    public void outputToTelemetry(LinearOpMode opMode) {
        opMode.telemetry.addData("Intake Power", mIntakeMotor.getPower());
        opMode.telemetry.addData("Intake Servo", mIntakeServo.getPower());
    }

    @Override
    public void stop() {
        WantedState(WantedState.WANTS_STOP);
    }

    @Override
    public void zeroSensors() {

    }
}