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

        Robot.getDrive().DriveControlState(Drive.DriveControlStates.OPERATOR_CONTROL);
        Robot.getJewel().retract();

        while (opModeIsActive() && !isStopRequested()){
            try {
                double leftspeed, rightspeed, xvalue, yvalue;

                //Values from gamepads with modifications
                xvalue = -power3MaintainSign(gamepad1.right_stick_x);
                yvalue = -power3MaintainSign(gamepad1.left_stick_y);

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;

                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);

                if (driver_station.level1()){
                    Robot.getLift().goTo(GlyphLypht.Mode.Level1);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().left();
                } else if (driver_station.level2()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().left();
                } else if (gamepad2.y) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level3);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().left();
                } else if (gamepad2.x) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level4);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().left();
                } else if (gamepad2.dpad_down) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    Robot.getIntake().intakeTwo(1);
                    Robot.getJewel().retract();
                }

                if (gamepad2.right_bumper){
                    Robot.getIntake().intakeOne(1);
                    Robot.getJewel().retract();
                } else if (!gamepad2.right_bumper && driver_station.outtake()){
                    Robot.getIntake().intakeTwo(-1);
                    Robot.getJewel().outtake();
                } else {
                    Robot.getIntake().intakeTwo(0.0);
                    Robot.getJewel().left();
                }


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
