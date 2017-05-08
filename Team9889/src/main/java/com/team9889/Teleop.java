package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.*;

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

    //Drivetrain
    private double leftspeed, rightspeed, xvalue, yvalue;
    private int div = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        Constants.OpMode = "TELEOP";

        waitForTeamStart(this);

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

                //Flywheel
                if(gamepad2.a){
                    mFlywheel.WantedState(Flywheel.WantedState.ON);
                }else {
                    mFlywheel.WantedState(Flywheel.WantedState.OFF);
                }


                //Intake ctrl
                if(Math.abs(gamepad2.right_trigger) > 0.01){
                    mIntake.WantedState(Intake.WantedState.WANTS_INTAKE);
                }else if(gamepad2.left_bumper) {
                    mIntake.WantedState(Intake.WantedState.WANTS_REVERSE);
                }else {
                    mIntake.WantedState(Intake.WantedState.WANTS_WAIT);
                }

                //Beacon pressing
                if(mDrive.getUltrasonic()<35){
                    if(beacontimer.milliseconds() > 20){
                        deploy = true;
                    }
                }else {
                    deploy = false;
                    beacontimer.reset();
                }

                if(!(deploy || gamepad1.right_bumper)){
                    mBeacon.WantedState(Beacon.Position.BOTH_DEPLOYED);
                }else {
                    mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);
                }

            }

            //Lower the max speed of the robot
            if (gamepad1.left_trigger > 0.1){
                div = 4;
            }else {
                div = 1;
            }

            xvalue = -gamepad1.right_stick_x/div;
            yvalue = gamepad1.left_stick_y/div;

            leftspeed =  yvalue - xvalue;
            rightspeed = yvalue + xvalue;

            mDrive.setLeftRightPower(leftspeed, rightspeed);

            updateTelemtry(this);

            // Pause for metronome tick.  40 mS each cycle = update 25 times a second.
            waitForTick(40);
        }

        finalAction(this);
    }
}
