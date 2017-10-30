package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.subsystems.Drive;

import static com.team9889.lib.CruiseLib.power3MaintainSign;

/**
 * Created by joshua9889 on 4/17/17.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        driver_station.init(this); // New Driver station
        waitForTeamStart(this, false);

        try {
            Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);
        } catch (Exception e){

        }

        while (opModeIsActive() && !isStopRequested()){
            try {
                double leftspeed, rightspeed, xvalue, yvalue;

                //Values from gamepads with modifications
                yvalue = power3MaintainSign(-gamepad1.left_stick_y);
                xvalue = power3MaintainSign(-gamepad1.right_stick_x);

                //Values to output to motors
                leftspeed =  xvalue - yvalue;
                rightspeed = xvalue + yvalue;

                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);

                Robot.getJewel().retract();

                //Push Telemetry
                updateTelemetry();
            } catch (Exception e) {
                telemetry.addData("Exception", e);
                telemetry.addData("Try plugging in hardware","");
                telemetry.update();
            }

            idle();//Thread.yield();
        }

        finalAction();
    }

}
