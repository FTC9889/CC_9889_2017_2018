package com.team9889.Linear;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Constants;
import com.team9889.Linear.subsystems.*;

import static com.team9889.lib.CruiseLib.calcLeftTankDrive;
import static com.team9889.lib.CruiseLib.calcRightTankDrive;

/**
 * Created by Joshua on 4/17/17.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    //Beacon Pushers
    private boolean deploy = false;
    private ElapsedTime beacontimer = new ElapsedTime();

    //Shooter
    private boolean SmartShot = false;
    private ElapsedTime shot  = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        UseCamera = false;
        waitForTeamStart(this);

        mSuperstructure.getDrive().DriveControlState(Drive.DriveControlState.OPERATOR_CONTROL);

        //Drive
        while (opModeIsActive() && !isStopRequested()){

            //Smart Shot
            if(gamepad1.right_trigger > 0.1){

                //Prevent Particles from getting stuck in between bumpers
                //mSuperstructure.getBeacon().WantedState(Beacon.Position.BOTH_RETRACTED);

                if (SmartShot) {
                    shot.reset();
                    SmartShot = false;
                }

                if(shot.milliseconds() > 1000){
                    mSuperstructure.getFlywheel().WantedState(Flywheel.WantedState.ON);
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_SHOOT);
                    if(shot.milliseconds() > 2000){
                        SmartShot = true;
                    }
                }else {
                    mSuperstructure.getFlywheel().WantedState(Flywheel.WantedState.ON);
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_WAIT);
                }

            }else {
                SmartShot = true;

                //Clear Flywheel//
                if(gamepad2.a){
                    mSuperstructure.getFlywheel().WantedState(Flywheel.WantedState.ON);
                }else {
                    mSuperstructure.getFlywheel().WantedState(Flywheel.WantedState.OFF);
                }
                //Clear Flywheel//


                //Start of Intake//

                if(Math.abs(gamepad2.right_trigger) > 0.01){
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_INTAKE);
                }else if(gamepad2.left_bumper) {
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_REVERSE);
                }else {
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_WAIT);
                }

                //End of Intake//

                //Start of Beacons//

                //Vote to Deploy Beacon Pressers Automatically if close to wall
                /*


                if(mSuperstructure.getDrive().getUltrasonic()<35){
                    if(beacontimer.milliseconds() > 20){
                        deploy = true;
                    }
                }else {
                    deploy = false;
                    beacontimer.reset();
                }

                //Deploy from Ultrasonic vote or gamepad button
                if(deploy || gamepad1.right_bumper){
                    mSuperstructure.getBeacon().WantedState(Beacon.Position.BOTH_DEPLOYED);
                }else {
                    mSuperstructure.getBeacon().WantedState(Beacon.Position.BOTH_RETRACTED);
                }

                */

                //End of Beacons//

                //Start of Drive//

                double leftspeed, rightspeed, xvalue, yvalue;
                int div;

                //Used to lower the max speed of the robot when lining up for shooting/beacons
                if (gamepad1.left_trigger > 0.3){
                    div = 4;
                }else {
                    div = 2;
                }

                //Values from gamepads with modifications
                xvalue = gamepad1.right_stick_x/div;
                yvalue = -gamepad1.left_stick_y/div;

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;


                //Set Motor Speeds
                mSuperstructure.getDrive().setLeftRightPower(leftspeed, rightspeed);

                //End of Drive//

            }

            //Push Telemetry
            updateTelemetry();

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            waitForTick(40);

        }

        finalAction();
    }

}
