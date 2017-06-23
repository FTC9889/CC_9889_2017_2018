package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.*;

import org.simbotics.robot.util.SimLib;

/**
 * Created by joshua on 4/17/17.
 */

@TeleOp (name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    //Beacon Pushers
    private boolean deploy = false;
    private ElapsedTime beacontimer = new ElapsedTime();

    //Shooter
    private boolean SmartShot = false;
    private ElapsedTime shot  = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.OpMode = "TELEOP";

        waitForTeamStart(this);

        mDrive.DriveControlState(Drive.DriveControlState.OPERATOR_CONTROL);

        //Drivetrain


        while (opModeIsActive() && !isStopRequested()){

            //Smart Shot
            if(gamepad1.right_trigger > 0.1){

                //Prevent Particles from getting stuck in between bumpers
                mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);

                if (SmartShot) {
                    shot.reset();
                    SmartShot = false;
                }

                if(shot.milliseconds() > 1000){
                    mFlywheel.WantedState(Flywheel.WantedState.ON);
                    mIntake.WantedState(Intake.WantedState.WANTS_SHOOT);
                    if(shot.milliseconds() > 2000){
                        SmartShot = true;
                    }
                }else {
                    mFlywheel.WantedState(Flywheel.WantedState.ON);
                    mIntake.WantedState(Intake.WantedState.WANTS_WAIT);
                }

            }else {
                SmartShot = true;

                //Clear Flywheel//
                if(gamepad2.a){
                    mFlywheel.WantedState(Flywheel.WantedState.ON);
                }else {
                    mFlywheel.WantedState(Flywheel.WantedState.OFF);
                }
                //Clear Flywheel//


                //Start of Intake//

                if(Math.abs(gamepad2.right_trigger) > 0.01){
                    mIntake.WantedState(Intake.WantedState.WANTS_INTAKE);
                }else if(gamepad2.left_bumper) {
                    mIntake.WantedState(Intake.WantedState.WANTS_REVERSE);
                }else {
                    mIntake.WantedState(Intake.WantedState.WANTS_WAIT);
                }

                //End of Intake//

                //Start of Beacons//

                //Vote to Deploy Beacon Pressers Automatically if close to wall
                if(mDrive.getUltrasonic()<35){
                    if(beacontimer.milliseconds() > 20){
                        deploy = true;
                    }
                }else {
                    deploy = false;
                    beacontimer.reset();
                }

                //Deploy from Ultrasonic vote or gamepad button
                if(deploy || gamepad1.right_bumper){
                    mBeacon.WantedState(Beacon.Position.BOTH_DEPLOYED);
                }else {
                    mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);
                }

                //End of Beacons//

                //Start of Drivetrain//

                double leftspeed, rightspeed, xvalue, yvalue;
                int div;

                //Used to lower the max speed of the robot when lining up for shooting/beacons
                if (gamepad1.left_trigger > 0.3){
                    div = 4;
                }else {
                    div = 1;
                }

                //Values from gamepads with modifications
                xvalue = gamepad1.right_stick_x/div;
                yvalue = gamepad1.left_stick_y/div;

                //Values to output to motors
                leftspeed = SimLib.calcLeftTankDrive(xvalue, yvalue);
                rightspeed = SimLib.calcRightTankDrive(xvalue, yvalue);

                //Set Motor Speeds
                mDrive.setLeftRightPower(leftspeed, rightspeed);

                //End of Drivetrain//

            }

            //Push Telemetry
            updateTelemetry(this);

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            waitForTick(40);

        }

        finalAction(this);
    }

}
