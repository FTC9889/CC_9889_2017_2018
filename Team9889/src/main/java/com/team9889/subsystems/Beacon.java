package com.team9889.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Beacon extends Subsystem {
    private static Beacon instance_ = new Beacon();

    public static Beacon getInstance(){
        return instance_;
    }

    //Beacon-pushing Servos
    private Servo RightBumper, LeftBumper;

    //MR Color Sensor
    private ColorSensor Color;

    public enum Position {
        BOTH_DEPLOYED, BOTH_RETRACTED, LEFT_DEPLOYED, RIGHT_DEPLOYED
    }

    public enum BeaconColor {
        Red, Blue
    }

    private Position mPosition_ = Position.BOTH_RETRACTED;

    @Override
    public void init(HardwareMap hardwareMap, boolean auton) {
        try {
            //Servos
            RightBumper = hardwareMap.servo.get(Constants.kRightBeaconPresserId);
            LeftBumper = hardwareMap.servo.get(Constants.kLeftBeaconPresserId);

            LeftBumper.setDirection(Servo.Direction.REVERSE);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("Beacon Servo Init Error");
        }

        try {
            //Color Sensor
            Color = hardwareMap.colorSensor.get(Constants.kColorSensorId);
            Color.enableLed(false);
        } catch (Exception e) {
            RobotLog.a(Constants.OpMode);
            RobotLog.a("Beacon Color Sensor Init Error");
        }
    }

    public void WantedState(Position wantedState) {
        mPosition_ = wantedState;
        switch (mPosition_){
            case BOTH_DEPLOYED:
                RightBumper.setPosition(Constants.kDeployedBeaconPresser);
                LeftBumper.setPosition(Constants.kDeployedBeaconPresser);
                return;
            case BOTH_RETRACTED:
                RightBumper.setPosition(Constants.kRetractedBeaconPresser);
                LeftBumper.setPosition(Constants.kRetractedBeaconPresser);
                return;
            case LEFT_DEPLOYED:
                RightBumper.setPosition(Constants.kRetractedBeaconPresser);
                LeftBumper.setPosition(Constants.kDeployedBeaconPresser);
                return;
            case RIGHT_DEPLOYED:
                RightBumper.setPosition(Constants.kDeployedBeaconPresser);
                LeftBumper.setPosition(Constants.kRetractedBeaconPresser);
                return;
            default:
                mPosition_ = Position.BOTH_RETRACTED;
                return;
        }
    }

    @Override
    public void zeroSensors() {}

    public BeaconColor getColor(){
        if(Color.red() < Color.blue()){
            return BeaconColor.Red;
        }else {
            return BeaconColor.Blue;
        }
    }

    @Override
    public void stop() {
        WantedState(Position.BOTH_RETRACTED);
    }

    @Override
    public void outputToTelemetry(LinearOpMode opMode) {
        opMode.telemetry.addData("Color Detected by Color Sensor", getColor());
    }
}
