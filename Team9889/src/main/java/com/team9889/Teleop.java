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

    private ElapsedTime matchTime = new ElapsedTime();

    public void runOpMode() throws InterruptedException {
        waitForStart(this, false);

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);
        Robot.getJewel().retract();
        matchTime.reset();
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

            // Control Arm and Wrist
            if (driver_station.level2()) {
                Robot.getJewel().right();

                Robot.getIntake().stopIntake();
                Robot.getIntake().clearArm();

                Robot.getLift().goTo(GlyphLypht.Mode.Level2);
            }
            else if (driver_station.level4()) {
                Robot.getJewel().right();

                Robot.getIntake().stopIntake();
                Robot.getIntake().retract();

                Robot.getLift().goTo(GlyphLypht.Mode.Level4);
            }
            else if (driver_station.intake()) {
                Robot.getJewel().retract();

                Robot.getIntake().intake();

                Robot.getLift().goTo(GlyphLypht.Mode.Intake);
            }

            // Control the fingers
            if(driver_station.outtake()){
                Robot.getLift().release();
            } else if(gamepad1.y){
                Robot.getLift().setServoPosition(0.4);
                Robot.getLift().clamp();
                Robot.getIntake().stopIntake();
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

            //Push Telemetry to phone
            telemetry.addData("Match Time", 120-matchTime.seconds());
            updateTelemetry();
            idle();
        }

        finalAction();
    }

}
