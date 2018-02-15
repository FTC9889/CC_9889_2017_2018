package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by joshua9889 on 2/3/2018.
 *
 * Used to control our relic mechanism.
 */

public class RelicArm extends Subsystem {

    // Hardware
    private DcMotorEx winch = null;
    private Servo elbow, finger = null;

    // Used to find the position we want with a
    // 8-rotation servo motor.
    private double oneRotation = 1.0/7.5;
    private double oneDegree = oneRotation/360;


    /**
     * Used to keep track of what the Relic arm is doing.
     */
    public enum RelicState{
        STOWED, RETRACTED, DEPLOYTOINTAKE, FIRSTZONE, SECONDZONE, THRIRDZONE
    }

    /**
     * Current State of the Relic Arm
     */
    private RelicState currentState = RelicState.STOWED;

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Current Relic State", currentState);
        telemetry.addData("Winch Position", winch.getCurrentPosition());
        telemetry.addData("Elbow Position", elbow.getPosition());
        telemetry.addData("Finger Position", finger.getPosition());
    }

    @Override
    public boolean init(Team9889Linear team9889Linear, boolean auton) {
        try {
            winch = team9889Linear.hardwareMap.get(DcMotorEx.class, Constants.kRelicMotor);
            elbow = team9889Linear.hardwareMap.get(Servo.class, Constants.kElbowServo);
            finger = team9889Linear.hardwareMap.get(Servo.class, Constants.kFinger);

            winch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            winch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            winch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            winch.setPower(0);
            winch.setTargetPosition(0);

            elbow.setDirection(Servo.Direction.REVERSE);

            finger.setDirection(Servo.Direction.REVERSE);

        } catch (Exception e){
            return false;
        }

        if(auton){
            openFinger();
            stow();
            winchGoTo(RelicState.STOWED);
        }

        return true;
    }

    @Override
    public void stop() {
        winch.setPower(0.0);
    }

    @Override
    public void zeroSensors() {
        while (winch.getCurrentPosition()!=0)
            winch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    /**
     * Open finger to release Relic
     */
    public void openFinger(){
        finger.setPosition(0.0);
    }

    /**
     * Close finger to hold Relic
     */
    public void closeFinger(){
        finger.setPosition(0.8);
    }

    /**
     * Stow the Arm so we are legal.
     */
    private void stow(){
        elbow.setPosition(oneDegree*270);
    }

    /**
     * @param wantedState Wanted Position of the winch motor.
     *                    Encoder Ticks/Positions are preset.
     */
    // TODO: Find the positions for the motor
    private void winchGoTo(RelicState wantedState){
        switch (wantedState){
            case RETRACTED:

                break;
            case DEPLOYTOINTAKE:

                break;
            case FIRSTZONE:

                break;
            case SECONDZONE:

                break;
            case THRIRDZONE:

                break;
            default:
                winch.setTargetPosition(0);
                break;
        }

        winch.setPower(0.3);
    }

    /**
     * @param wantedState Wanted State of the Relic Arm.
     */
    public void goTo(RelicState wantedState){
        if(wantedState!=currentState){
            switch (wantedState){
                case RETRACTED:

                    break;
                case DEPLOYTOINTAKE:

                    break;
                case FIRSTZONE:

                    break;
                case SECONDZONE:

                    break;
                case THRIRDZONE:

                    break;
                default:
                    winchGoTo(RelicState.STOWED);
                    currentState = RelicState.STOWED;
                    break;
            }
        }
    }


}
