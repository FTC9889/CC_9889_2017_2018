package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 4/17/17.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        driver_station.init(this);
        waitForTeamStart(this, false);
        mSuperstructure.getDrive().DriveControlState(Drive.DriveControlState.OPERATOR_CONTROL);

        //Drive
        while (opModeIsActive() && !isStopRequested()){

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
            xvalue = gamepad1.right_stick_x/div;
            yvalue = -gamepad1.left_stick_y/div;

            //Values to output to motors
            leftspeed =  yvalue - xvalue;
            rightspeed = yvalue + xvalue;


            //Set Motor Speeds
            try{
                mSuperstructure.getDrive().setLeftRightPower(leftspeed, rightspeed);
            }catch (Exception e){
                telemetry.addData("Exception", e);
                mSuperstructure.getDrive().stop();
            }

            //End of Drive//

            //Push Telemetry
            updateTelemetry();

            idle();
        }

        finalAction();
    }

}
