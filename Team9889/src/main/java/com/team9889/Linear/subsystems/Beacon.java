package com.team9889.Linear.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Beacon extends Subsystem {

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
    public boolean init(HardwareMap hardwareMap, boolean auton) {
        boolean error = false;
        try {
            //Servos
            RightBumper = hardwareMap.servo.get(Constants.kRightBeaconPresserId);
            LeftBumper = hardwareMap.servo.get(Constants.kLeftBeaconPresserId);

            LeftBumper.setDirection(Servo.Direction.REVERSE);
        } catch (Exception e) {
            error = true;
        }

        try {
            //Color Sensor
            Color = hardwareMap.colorSensor.get(Constants.kColorSensorId);
            Color.enableLed(false);
        } catch (Exception e) {
            error = false;
        }

        this.zeroSensors();
        this.WantedState(Beacon.Position.BOTH_RETRACTED);

        return !error;
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
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Color Detected by Color Sensor", getColor());
    }
}
