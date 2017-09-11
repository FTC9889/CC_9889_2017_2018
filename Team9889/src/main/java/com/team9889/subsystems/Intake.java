package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by Joshua on 4/10/2017.
 */

public class Intake extends Subsystem {

    private DcMotor mIntakeMotor;
    private CRServo mIntakeServo;

    @Override
    public boolean init(HardwareMap hardwareMap, boolean auton){
        boolean error = false;
        try {
            mIntakeMotor = hardwareMap.dcMotor.get(Constants.kIntakeMotorId);
        } catch (Exception e) {
            error = true;
        }

        try {
            mIntakeServo = hardwareMap.crservo.get(Constants.kIntakeServo);
        } catch (Exception e) {
            error = true;
        }

        this.zeroSensors();
        this.WantedState(WantedState.WANTS_STOP);

        return !error;
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
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
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
