package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.hardware.rev.motorStuff.OpenDcMotor;
import org.openftc.hardware.rev.motorStuff.OpenDcMotorImpl;

/**
 * Created by joshua9889 on 2/3/2018.
 *
 * Used to control our relic mechanism.
 */

public class Relic extends Subsystem {

    // Hardware
    private DcMotor winch = null;
    private Servo elbow, finger = null;

    // Used to find the position we want with a
    // 8-rotation servo motor.
    private double oneRotation = 1.0/7.5;
    private double oneDegree = oneRotation/360;

    private int modifier = 0;
    private int lastModifier = 0;

    /**
     * Used to keep track of what the Relic arm is doing.
     */
    public enum RelicState{
        STOWED, RETRACTED, DEPLOYTOINTAKE, FIRSTZONE, SECONDZONE, THRIRDZONE, CLOSE
    }

    /**
     * Current State of the Relic Arm
     */
    private RelicState currentState = RelicState.STOWED;

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
        telemetry.addData("Current Relic State", currentState);
        telemetry.addData("Is In Position", isInPosition());
        telemetry.addData("Winch Position", winch.getCurrentPosition());
        telemetry.addData("Elbow Position", elbow.getPosition());
        telemetry.addData("Finger Position", finger.getPosition());
    }

    @Override
    public boolean init(Team9889Linear team9889Linear, boolean auton) {
        try {
            winch = team9889Linear.hardwareMap.get(DcMotor.class, Constants.kRelicMotor);
            elbow = team9889Linear.hardwareMap.get(Servo.class, Constants.kElbowServo);
            finger = team9889Linear.hardwareMap.get(Servo.class, Constants.kFinger);

            winch.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
            winch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            elbow.setDirection(Servo.Direction.REVERSE);

            finger.setDirection(Servo.Direction.REVERSE);

        } catch (Exception e){
            RobotLog.a("Error: "+ String.valueOf(e));
            return false;
        }

        if(auton){
			this.zeroSensors();
            this.openFinger();
            this.winchGoTo(RelicState.STOWED);
            currentState = RelicState.STOWED;
        }

        return true;
    }

    @Override
    public void stop() {
        winch.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        winch.setPower(0.0);
    }

    @Override
    public void zeroSensors() {
        while (winch.getCurrentPosition()!=0)
            winch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		winch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void test(Telemetry telemetry) {

    }

    /**
     * Open finger to release Relic
     */
    public void openFinger(){
        finger.setPosition(0.0);
        if(currentState==RelicState.THRIRDZONE)
            elbow.setPosition(oneDegree*0);
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
        elbow.setPosition(oneDegree*300);
    }

    public void elbowDeploy(){
        elbow.setPosition(oneDegree*30);
    }

    public void elbowRetract(){
        elbow.setPosition(oneDegree*175);
    }

    /**
     * @param wantedState Wanted Position of the winch motor.
     *                    Encoder Ticks/Positions are preset.
     */
    // TODO: Find the positions for the motor
    private void winchGoTo(RelicState wantedState){
        switch (wantedState){
            case CLOSE:
                winch.setTargetPosition(874+modifier);
                winch.setPower(1);
                break;
            case RETRACTED:
                winch.setTargetPosition(874+modifier);
                winch.setPower(1);
                break;
            case DEPLOYTOINTAKE:
                winch.setTargetPosition(2697+modifier);
                winch.setPower(1);
                break;
            case FIRSTZONE:
                winch.setTargetPosition(2697+modifier);
                winch.setPower(1);
                break;
            case SECONDZONE:
                winch.setTargetPosition(5000+modifier);
                winch.setPower(1);
                break;
            case THRIRDZONE:
                if(8520+modifier>8530)
                    winch.setTargetPosition(8520+modifier);
                else
                    winch.setTargetPosition(8520);
                winch.setPower(1);
                break;
            default:
                if(0+modifier>0)
                    winch.setTargetPosition(0+modifier);
                else
                    winch.setTargetPosition(0);
                winch.setPower(1);
                break;
        }
    }

    /**
     * @param wantedState Wanted State of the Relic Arm.
     */
    //TODO: Something doesn't work all the time with this logic... i don't know what it is...
    public void goTo(RelicState wantedState){
        if(wantedState!=currentState){
            switch (wantedState){
                case CLOSE:
                    winchGoTo(RelicState.CLOSE);
                    elbowDeploy();
                    currentState=wantedState;
                    break;
                case STOWED:
                    winchGoTo(RelicState.STOWED);
                    stow();
                    currentState=wantedState;
                    break;
                case RETRACTED:
                    winchGoTo(RelicState.RETRACTED);
                    if(Math.abs(winch.getTargetPosition()-winch.getCurrentPosition())<10 && currentState!=RelicState.STOWED){
                        elbowRetract();
                        currentState=wantedState;
                    } else if(currentState==RelicState.STOWED){
                        elbowRetract();
                    }
                    break;
                case DEPLOYTOINTAKE:
                    elbowDeploy();
                    winchGoTo(RelicState.DEPLOYTOINTAKE);
                    currentState=wantedState;
                    break;
                case FIRSTZONE:
                    if(currentState==RelicState.RETRACTED){
                        stow();
                    }

                    winchGoTo(RelicState.FIRSTZONE);
                    elbowDeploy();
                    currentState=wantedState;
                    break;
                case SECONDZONE:
                    if(currentState==RelicState.RETRACTED){
                        stow();
                    }

                    winchGoTo(RelicState.SECONDZONE);
                    elbowDeploy();
                    currentState=wantedState;
                    break;
                case THRIRDZONE:
                    if(currentState==RelicState.RETRACTED){
                        stow();
                    }

                    winchGoTo(RelicState.THRIRDZONE);
                    currentState=wantedState;
                    break;
            }
        } else {
            if(lastModifier!=modifier)
                winchGoTo(currentState);

            if(currentState==RelicState.THRIRDZONE)
                if(winch.getCurrentPosition()>6000){
                    elbow.setPosition(oneDegree*30);
                } else {
                    elbowRetract();
                }
            else if(currentState==RelicState.DEPLOYTOINTAKE)
                elbowDeploy();
        }
    }

    public void setModifier(int modifier){
        this.modifier = modifier;
    }

    public boolean isInPosition(){
        return Math.abs(winch.getCurrentPosition()-winch.getTargetPosition())<10;
    }

//    public double getCurrent(){
//        return winch.getCurrentDraw().doubleValue;
//    }

}
