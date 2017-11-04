package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.subsystems.Drive;
import com.team9889.subsystems.GlyphLypht;

import static com.team9889.lib.CruiseLib.power3MaintainSign;

/**
 * Created by joshua9889 on 4/17/17.
 */

@TeleOp(name = "Teleop")
public class Teleop extends Team9889LinearOpMode {

    int liftPos = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        driver_station.init(this); // New Driver station
        waitForTeamStart(this, false);

        try {
            Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);
        } catch (Exception e){

        }

        while (opModeIsActive() && !isStopRequested()){
            try {
                double leftspeed, rightspeed, xvalue, yvalue;

                //Values from gamepads with modifications
                xvalue = power3MaintainSign(-gamepad1.right_stick_x);
                yvalue = power3MaintainSign(-gamepad1.left_stick_y);

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;

                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);

                Robot.getJewel().retract();

                if (gamepad2.a){
                    Robot.getLift().goTo(GlyphLypht.Mode.Level1);
                    Robot.getIntake().waitToScore();
                } else if (gamepad2.b) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                    Robot.getIntake().waitToScore();
                } else if (gamepad2.x) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level3);
                    Robot.getIntake().waitToScore();
                } else if (gamepad2.y) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level4);
                    Robot.getIntake().waitToScore();
                } else if (gamepad2.dpad_down) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    Robot.getIntake().intakeTwo(1);
                }

                if (gamepad2.right_trigger >    0.4)
                    Robot.getIntake().intakeOne(1);
                else if (gamepad2.left_trigger > 0.4)
                    Robot.getIntake().waitToScore();
                else if (gamepad2.left_bumper || gamepad1.x)
                    Robot.getIntake().intakeTwo(-1);

                //Push Telemetry
                updateTelemetry();
            } catch (Exception e) {
                telemetry.addData("Exception", e);
                telemetry.addData("Try plugging in hardware","");
                telemetry.update();
            }

            idle();//Thread.yield();
        }

        finalAction();
    }

}
