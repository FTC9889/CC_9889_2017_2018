package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.subsystems.GlyphLypht;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 4/17/17.
 * Our Teleop Code
 */

@TeleOp
public class Teleop extends Team9889Linear {

    boolean debug = false;
    private ElapsedTime matchTime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        waitForStart(this, false);

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);
        Robot.getJewel().retract();

        matchTime.reset();

        Thread glyphThread = new Thread(new GlyphRunnable());

        Thread driveThread = new Thread(new DriveRunnable());

        Thread intakeThread = new Thread(new IntakeRunnable());


        glyphThread.start();
        driveThread.start();
        intakeThread.start();

        do {
            //Push Telemetry to phone
            telemetry.addData("Match Time", 120-matchTime.seconds());
            updateTelemetry();
            idle();
        } while (opModeIsActive() && !isStopRequested() && 120-matchTime.seconds()>0);

        finalAction();
    }

    // Control Arm and Wrist
    private class GlyphRunnable implements Runnable {
        private GlyphLypht.Mode currentMode = GlyphLypht.Mode.Teleop;

        @Override
        public void run(){
            while(opModeIsActive() && !isStopRequested()){

                if(gamepad1.y){
                    Robot.getIntake().outtake();
                    sleep(700);
                    Robot.getLift().setServoPosition(0.4);
                    Robot.getLift().clamp();
                    Robot.getIntake().stopIntake();

                    sleep(400);

                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                    while(opModeIsActive() && !Robot.getLift().isAtLocation()){
                        Thread.yield();
                    }
                    sleep(100);
                    Robot.getIntake().retract();
                    currentMode = GlyphLypht.Mode.Level2;
                }

                if(gamepad1.dpad_down){
                    Robot.getIntake().leftRetract();
                    sleep(250);
                    Robot.getIntake().rightRetract();
                    sleep(250);
                    Robot.getIntake().intake();
                }

                if (driver_station.level2()) {
                    if(currentMode == GlyphLypht.Mode.Intake){
                        Robot.getLift().setServoPosition(0.4);
                        Robot.getLift().clamp();
                        Robot.getIntake().stopIntake();
                        sleep(400);
                    }

                    Robot.getJewel().right();

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
                else if (driver_station.level4()) {
                    if(currentMode == GlyphLypht.Mode.Intake){
                        Robot.getLift().setServoPosition(0.4);
                        Robot.getLift().clamp();
                        Robot.getIntake().stopIntake();
                        sleep(400);
                    }
                    Robot.getJewel().right();

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
                else if (driver_station.intake()) {
                    Robot.getJewel().right();

                    Robot.getIntake().intake();

                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    currentMode = GlyphLypht.Mode.Intake;
                }
                else if(gamepad1.dpad_up) {
                    if(currentMode == GlyphLypht.Mode.Intake){
                        Robot.getLift().setServoPosition(0.4);
                        Robot.getLift().clamp();
                        Robot.getIntake().stopIntake();
                        sleep(400);
                    }
                    Robot.getJewel().right();

                    Robot.getIntake().stopIntake();
                    Robot.getIntake().clearArm();

                    Robot.getLift().goTo(GlyphLypht.Mode.OvertheBack);
                    currentMode = GlyphLypht.Mode.OvertheBack;
                }
                idle();
            }
        }
    }

    private class DriveRunnable implements Runnable{
        @Override
        public void run(){
            while (opModeIsActive() && !isStopRequested()){
                double leftspeed, rightspeed, xvalue, yvalue;

                //Values from gamepads with modifications
                xvalue = -gamepad1.right_stick_x;
                yvalue = -gamepad1.left_stick_y;

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;

                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);

                if(debug) {
                    RobotLog.d("Y:" + String.valueOf(gamepad1.left_stick_y));
                    RobotLog.d("X:" + String.valueOf(gamepad1.right_stick_x));
                    RobotLog.d("-----------Left:" + String.valueOf(leftspeed));
                    RobotLog.d("-----------Right:" + String.valueOf(rightspeed));
                    sleep(5);
                }

                idle();
            }
        }
    }

    private class IntakeRunnable implements Runnable{
        @Override
        public void run(){
            while(opModeIsActive() && !isStopRequested()){
                // Control the fingers
                if(driver_station.outtake()){
                    Robot.getLift().release();
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
                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    Robot.getIntake().intake();
                }
                idle();
            }
        }
    }

}
