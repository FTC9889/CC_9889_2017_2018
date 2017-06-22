package com.team9889.Iterative.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;

/**
 * Created by joshua on 6/21/17.
 */

public class Intake implements Subsystem {
    private static Intake instance_ = new Intake();

    public static Intake getInstance(){
        return instance_;
    }

    private DcMotor mIntakeMotor;
    private CRServo mIntakeServo;

    public void init(OpMode opMode, boolean auton){
        try {
            mIntakeMotor = opMode.hardwareMap.dcMotor.get(Constants.kIntakeMotorId);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("Intake DC Init Error");
        }

        try {
            mIntakeServo = opMode.hardwareMap.crservo.get(Constants.kIntakeServo);
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
    public void outputToTelemtry(OpMode opMode) {
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
