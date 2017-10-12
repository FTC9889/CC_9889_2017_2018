package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.subsystems.DriveControlStates;

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

        Robot.getDrive().DriveControlState(DriveControlStates.OPERATOR_CONTROL);

        while (opModeIsActive() && !isStopRequested()){
            try {
                //Start of Drive//
                double leftspeed, rightspeed, xvalue, yvalue;
                int div;

                //Used to lower the max speed of the robot when lining up for shooting/beacons
                if (driver_station.SlowDrivetrain()){
                    div = 4;
                }else {
                    div = 1;
                }

                //Values from gamepads with modifications
                xvalue = power3MaintainSign(gamepad1.right_stick_x)/div;
                yvalue = -power3MaintainSign(gamepad1.left_stick_y)/div;

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;


                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);


                //End of Drive//

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
