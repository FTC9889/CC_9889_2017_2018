package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.Beacon;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.Flywheel;
import com.team9889.subsystems.Intake;

/**
 * Created by Joshua on 4/17/17.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    //New Driver Station
    Driver_Station driver_station = new Driver_Station();

    //Beacon Pushers
    private boolean deploy = false;
    private ElapsedTime beacontimer = new ElapsedTime();

    //Shooter
    private boolean SmartShot = false;
    private ElapsedTime shot  = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        driver_station.init(this);
        waitForTeamStart(this, false);

        mSuperstructure.getDrive().DriveControlState(Drive.DriveControlState.OPERATOR_CONTROL);

        //Drive
        while (opModeIsActive() && !isStopRequested()){

            //Smart Shot
            if(driver_station.ShootBoolean()){

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
                if(driver_station.ClearParticlesFromFlywheel()){
                    mSuperstructure.getFlywheel().WantedState(Flywheel.WantedState.ON);
                }else {
                    mSuperstructure.getFlywheel().WantedState(Flywheel.WantedState.OFF);
                }
                //Clear Flywheel//


                //Start of Intake//

                if(driver_station.Intake()){
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_INTAKE);
                }else if(driver_station.Outtake()) {
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_REVERSE);
                }else {
                    mSuperstructure.getIntake().WantedState(Intake.WantedState.WANTS_WAIT);
                }

                //End of Intake//

                //Start of Beacons//

                //Vote to Deploy Beacon Pressers Automatically if close to wall
                if(mSuperstructure.getDrive().getUltrasonic()<35){
                    if(beacontimer.milliseconds() > 20){
                        deploy = true;
                    }
                }else {
                    deploy = false;
                    beacontimer.reset();
                }

                //Deploy from Ultrasonic vote or gamepad button
                if(deploy || driver_station.BeaconDeploy()){
                    mSuperstructure.getBeacon().WantedState(Beacon.Position.BOTH_DEPLOYED);
                }else {
                    mSuperstructure.getBeacon().WantedState(Beacon.Position.BOTH_RETRACTED);
                }

                //End of Beacons//

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

            }

            //Push Telemetry
            updateTelemetry();

            idle();
        }

        finalAction();
    }

}
