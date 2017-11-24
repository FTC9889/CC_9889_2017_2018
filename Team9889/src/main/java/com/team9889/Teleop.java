package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

import static com.team9889.lib.CruiseLib.power3MaintainSign;

/**
 * Created by joshua9889 on 4/17/17.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    private ElapsedTime matchTime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        driver_station.init(this); // New Driver station
        waitForTeamStart(this, false);

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);
        Robot.getJewel().retract();

        matchTime.reset();

        while (opModeIsActive() && !isStopRequested() && matchTime.seconds() < 120){
            try {
                double leftspeed, rightspeed, xvalue, yvalue;

                //Values from gamepads with modifications
                xvalue = -power3MaintainSign(gamepad1.right_stick_x);
                yvalue = -power3MaintainSign(gamepad1.left_stick_y);

                yvalue /= 1.2;

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;

                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);

                if (driver_station.level2()) {
                    Robot.getJewel().right();
                    Robot.getIntake().stopIntake();
                    Robot.getIntake().clearArm();
                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                } else if (driver_station.level4()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level4);
                    Robot.getJewel().right();
                    Robot.getIntake().stopIntake();
                    Robot.getIntake().retract();
                } else if (driver_station.intake()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    Robot.getIntake().intake();
                    Robot.getJewel().retract();
                }

                if(driver_station.outtake()){
                    Robot.getLift().release();
                } else if(driver_station.level3()){
                    Robot.getLift().clamp();
                    Robot.getIntake().stopIntake();
                }

                if(gamepad1.y)
                    Robot.getLift().clamp();

                if(gamepad2.left_bumper)
                    Robot.getIntake().retract();
                else if(gamepad2.b)
                    Robot.getIntake().leftRetract();
                else if(gamepad2.x)
                    Robot.getIntake().rightRetract();

                if(gamepad2.a)
                    Robot.getIntake().outtake();
                else if(gamepad2.y)
                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    Robot.getIntake().intake();

                //Push Telemetry
                updateTelemetry();
            } catch (Exception e) {
                telemetry.addData("Exception", e);
                telemetry.addData("Try plugging in hardware","");
                telemetry.update();
            }

            idle();
        }

        finalAction();
    }

}
