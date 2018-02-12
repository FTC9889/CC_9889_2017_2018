package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.team9889.Constants;
import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.openftc.hardware.rev.motorStuff.OpenDcMotor;

/**
 * Created by joshua9889 on 2/3/2018.
 */

public class RelicArm extends Subsystem {

    private DcMotorEx relicArm = null;
    private Servo relicElbow = null;
    private Servo relicFinger = null;

    /*
    FOR FINGER
    1 = open
    .2 = closed
    */

    @Override
    public void outputToTelemetry(Telemetry telemetry) {
		telemetry.addData("Relic Power", relicArm.getPower());
//        telemetry.addData("Relic Draw", relicArm.getCurrentDraw().doubleValue);
    }

    @Override
    public boolean init(Team9889Linear team9889Linear, boolean auton) {
        try {
            relicArm = team9889Linear.hardwareMap.get(DcMotorEx.class,Constants.kRelicMotor);
            relicArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            relicArm.setPower(0);

            relicElbow = team9889Linear.hardwareMap.get(Servo.class,Constants.kVexMotor);
            relicFinger = team9889Linear.hardwareMap.get(Servo.class,Constants.kFinger);
            relicElbow.setPosition(0.51309);
            relicFinger.setPosition(1);

        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public void zeroSensors() {

    }
}
