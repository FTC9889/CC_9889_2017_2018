package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

/**
 * Created by joshua9889 on 4/17/17.
 * Our Teleop Code
 */

@TeleOp
public class Teleop extends Team9889Linear {

    ElapsedTime matchTime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        waitForStart(false);

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);

        // Timer for Displaying time on DS
        matchTime.reset();

        // Start control Threads
        new Thread(new GlyphRunnable()).start();
        new Thread(new DriveRunnable()).start();
        new Thread(new IntakeRunnable()).start();

        // Loop while the match is happening
        while (opModeIsActive() && !isStopRequested() && 120-matchTime.seconds()>0){
            // Retract Jewel arm
            Robot.getJewel().stop();

            //Push Telemetry to phone
            telemetry.addData("Match Time", 120-matchTime.seconds());
            updateTelemetry();

            idle();
        }

        // Stop everything
        finalAction();
    }

    // Control Arm and Wrist
    private class GlyphRunnable implements Runnable {
        private GlyphLypht.Mode currentMode = GlyphLypht.Mode.Teleop;

        @Override
        public void run(){
            while(opModeIsActive() && !isStopRequested()){

                // Outtake one glyph and deploy single
                // glyph to level 2
                if(gamepad1.y){
                    Robot.getIntake().outtake();
                    sleep(700);
                    Robot.getLift().setServoPosition(0.4);
                    Robot.getLift().clamp();
                    Robot.getIntake().stopIntake();

                    sleep(400);

                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                    ElapsedTime t = new ElapsedTime();
                    while(opModeIsActive() && !Robot.getLift().isAtLocation() && t.milliseconds()<1000){
                        Thread.yield();
                    }
                    sleep(100);
                    Robot.getIntake().retract();
                    currentMode = GlyphLypht.Mode.Level2;
                }

                // Go to level 2
                if (driver_station.level2()) {
                    if(currentMode == GlyphLypht.Mode.Intake){
                        Robot.getLift().setServoPosition(0.4);
                        Robot.getLift().clamp();
                        Robot.getIntake().stopIntake();
                        sleep(400);
                    }

                    Robot.getIntake().stopIntake();
                    Robot.getIntake().clearArm();


                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                    while(opModeIsActive() && !Robot.getLift().isAtLocation()){
                        Thread.yield();
                    }
                    sleep(100);
                    Robot.getIntake().retract();
                    currentMode = GlyphLypht.Mode.Level2;
                }
                // Go to top level
                else if (driver_station.level4()) {
                    if(currentMode == GlyphLypht.Mode.Intake){
                        Robot.getLift().setServoPosition(0.4);
                        Robot.getLift().clamp();
                        Robot.getIntake().stopIntake();
                        sleep(400);
                    }

                    if(currentMode!= GlyphLypht.Mode.Level2){
                        Robot.getIntake().stopIntake();
                        Robot.getIntake().clearArm();

                        Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                        while(opModeIsActive() && !Robot.getLift().isAtLocation()){
                            Thread.yield();
                        }
                    }

                    Robot.getLift().goTo(GlyphLypht.Mode.Level4);
                    sleep(100);
                    Robot.getIntake().retract();
                    currentMode = GlyphLypht.Mode.Level4;
                }
                // Go to Intaking
                else if (driver_station.intake()) {
                    Robot.getIntake().intake();

                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    currentMode = GlyphLypht.Mode.Intake;
                }
                // Over-the-back scoring
                else if(gamepad1.dpad_up) {
                    if(currentMode == GlyphLypht.Mode.Intake){
                        Robot.getLift().setServoPosition(0.4);
                        Robot.getLift().clamp();
                        Robot.getIntake().stopIntake();
                        sleep(400);
                    }

                    Robot.getIntake().stopIntake();
                    Robot.getIntake().clearArm();

                    Robot.getLift().goTo(GlyphLypht.Mode.OvertheBack);
                    currentMode = GlyphLypht.Mode.OvertheBack;
                }
                idle();
            }
        }
    }

    // Control Drivetrain
    private class DriveRunnable implements Runnable{
        @Override
        public void run(){
            while (opModeIsActive() && !isStopRequested()){
                double leftspeed, rightspeed, turn, speed;

                //Values from gamepads with modifications
                turn = gamepad1.right_stick_x;
                speed = -gamepad1.left_stick_y;

                double left = speed + turn;
                double right = speed - turn;

                Robot.getDrive().setLeftRightPower(left, right);

                // Auto brake to make it easier
                // to get up on balance stone
                if(matchTime.seconds()>110)
                    Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
                else
                    Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);

                idle();
            }
        }
    }

    // Control Intake
    private class IntakeRunnable implements Runnable{
        @Override
        public void run(){
            while(opModeIsActive() && !isStopRequested()){
                // Control the fingers
                if(driver_station.outtake()){
                    Robot.getLift().release();
                }

                // A quick preset to make it easier
                // to get a glyph in.
                if(gamepad2.dpad_down){
                    Robot.getIntake().leftRetract();
                    sleep(500);
                    Robot.getIntake().rightRetract();
                    sleep(500);
                    Robot.getIntake().intake();
                }

                // Control the intake
                if(gamepad2.left_bumper)
                    Robot.getIntake().retract();
                else if(gamepad2.b)
                    Robot.getIntake().leftRetract();
                else if(gamepad2.x)
                    Robot.getIntake().rightRetract();

                if(gamepad2.a){
                    Robot.getIntake().outtake();
                } else if(gamepad2.y){
                    Robot.getIntake().outtake();
                    Robot.getIntake().clearArm();
                }

                idle();
            }
        }
    }

}
