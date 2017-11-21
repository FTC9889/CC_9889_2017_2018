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

                //Values to output to motors
                leftspeed =  yvalue - xvalue;
                rightspeed = yvalue + xvalue;

                //Set Motor Speeds
                Robot.getDrive().setLeftRightPower(leftspeed, rightspeed);

                if (driver_station.level1()){
                    Robot.getLift().goTo(GlyphLypht.Mode.Level1);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().right();
                } else if (driver_station.level2()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level2);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().right();
                } else if (driver_station.level3()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level3);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().right();
                } else if (driver_station.level4()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Level4);
                    Robot.getIntake().waitToScore();
                    Robot.getJewel().right();
                } else if (driver_station.intake()) {
                    Robot.getLift().goTo(GlyphLypht.Mode.Intake);
                    Robot.getIntake().intakeTwo(1);
                    Robot.getJewel().retract();
                }

                if(gamepad1.right_trigger > 0.4){
                    Robot.getIntake().intakeTwo(-1);
                } else {
                    if (gamepad1.right_bumper){
                        Robot.getIntake().intake(1);
                    } else {
                        Robot.getIntake().intake(0);
                    }

                    if (gamepad1.left_bumper){
                        Robot.getIntake().intake(1);
                    }
                }

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
