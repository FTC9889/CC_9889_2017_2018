package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by joshua9889 on 11/11/2017.
 */

public class BalancingStone extends Subsystem {

    private Servo servo = null;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        opMode.telemetry.addData("Balance thing", servo.getPosition());
    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try {
            servo = team9889LinearOpMode.hardwareMap.servo.get(Constants.kBalanceThing);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void stop() {
        raise();
    }

    @Override
    public void zeroSensors() {}

    public void lower(){
        servo.setPosition(Constants.BalanceThingLowered);
    }

    public void raise(){
        servo.setPosition(Constants.BalanceThingRaised);
    }
}
