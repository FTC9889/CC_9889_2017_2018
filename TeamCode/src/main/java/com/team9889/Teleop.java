package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Relic;

/**
 * Created by joshua9889 on 4/17/17.
 * Our Teleop Code
 */

@TeleOp
public class Teleop extends Team9889Linear {

    ElapsedTime matchTime = new ElapsedTime();
    boolean intaking = false;
    private GlyphLypht.Mode currentMode = GlyphLypht.Mode.Teleop;
    private int modifier = 0;

    public void runOpMode() throws InterruptedException {
        waitForStart(false);

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);

        // Timer for Displaying time on DS
        matchTime.reset();

        // Start control Threads
        new Thread(new GlyphRunnable()).start();
        new Thread(new IntakeRunnable()).start();

        // Loop while the match is happening
        while (opModeIsActive() && !isStopRequested()){
            // Retract Jewel arm
            Robot.getJewel().stop();

            double turn = gamepad1.right_stick_x;
            double speed = -gamepad1.left_stick_y;

            double left = speed + turn;
            double right = speed - turn;

            if(currentMode == GlyphLypht.Mode.OvertheBack){
                left /= 2;
                right /= 2;
            }

            Robot.getDrive().setLeftRightPower(left, right);

            // Auto brake to make it easier
            // to get up on balance stone
            if(matchTime.seconds()>110)
                Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.BRAKE);
            else
                Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);

            idle();

            //Push Telemetry to phone
            telemetry.addData("Match Time", 120-matchTime.seconds());
            updateTelemetry();
            idle();
            sleep(20);
        }

        // Stop everything
        finalAction();
    }

    // Control Arm and Wrist
    private class GlyphRunnable implements Runnable {

        @Override
        public void run(){
            while(opModeIsActive() && !isStopRequested()){

                // Outtake one glyph and deploy single
                // glyph to level 2
                if(driver_station.outtakeAndLevel2()){
                    intaking=false;
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
                    intaking=false;
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
                    intaking=false;
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
                    intaking = true;

                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    currentMode = GlyphLypht.Mode.Intake;
                }
                // Over-the-back scoring
                else if(driver_station.overTheBack()) {
                    intaking=false;
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

    // Control Intake
    private class IntakeRunnable implements Runnable{
        private Relic.RelicState wantedState = Relic.RelicState.STOWED;
        private boolean firstRun = true;

        @Override
        public void run(){
            while(opModeIsActive() && !isStopRequested()){
                // Control the fingers
                if(driver_station.release()){
                    Robot.getLift().release();
                }

                // A quick preset to make it easier
                // to get a glyph in.
                if(driver_station.swivel()){
                    Robot.getIntake().leftRetract();
                    sleep(500);
                    Robot.getIntake().rightRetract();
                    sleep(500);
                    Robot.getIntake().intake();
                }

                // Control the intake
                if(driver_station.retract()) {
                    Robot.getIntake().retract();
                    intaking = false;
                }

                if(driver_station.outtake()){
                    intaking=false;
                    Robot.getIntake().outtake();
                    Robot.getIntake().clearArm();
                }

                if(gamepad2.dpad_down) {
                    wantedState = Relic.RelicState.DEPLOYTOINTAKE;
                    modifier=0;
                    firstRun = false;
                } else if(gamepad2.dpad_right) {
                    wantedState = Relic.RelicState.RETRACTED;
                    modifier=0;
                    firstRun = false;
                } else if(gamepad2.dpad_left) {
                    wantedState = Relic.RelicState.THRIRDZONE;
                    modifier=0;
                    firstRun = false;
                } else if(gamepad2.dpad_up){
                    wantedState = Relic.RelicState.CLOSE;
                    modifier=0;
                    firstRun = false;
                }

                if(!firstRun) {
                    Robot.getRelic().goTo(wantedState);

                    if (wantedState== Relic.RelicState.THRIRDZONE && Robot.getRelic().isInPosition()){
                        Robot.getRelic().openFinger();
                        wantedState= Relic.RelicState.RETRACTED;
                    }

                    if(gamepad2.left_bumper)
                        Robot.getRelic().elbowRetract();

                    if(gamepad2.right_stick_y < -0.2)
                        modifier+=20;
                    else if(gamepad2.right_stick_y>0.2)
                        modifier-=20;

                    if(gamepad2.left_stick_y < -0.2)
                        modifier+=4;
                    else if(gamepad2.left_stick_y>0.2)
                        modifier-=4;

                    Robot.getRelic().setModifier(modifier);
                }

                if(gamepad2.left_stick_button)
                    Robot.getRelic().closeFinger();
                else if(gamepad2.right_stick_button)
                    Robot.getRelic().openFinger();
                idle();
            }
        }
    }

}
