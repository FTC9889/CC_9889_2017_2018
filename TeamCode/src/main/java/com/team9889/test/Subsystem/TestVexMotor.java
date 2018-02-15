package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Constants;


/**
 * Created by joshua9889 on 1/26/2018.
 */

@TeleOp
@Disabled
public class TestVexMotor extends LinearOpMode {

    double firstPos = 0.51309;
    double pos = firstPos;

    enum States{
        Protected, HalfWay, Full
    }

    States wantedState = States.Protected;
    States currentState = States.Protected;
    ElapsedTime t = new ElapsedTime();
    boolean time = false;
    int waitTime;

    @Override
    public void runOpMode() throws InterruptedException {
        ///Servo servo = hardwareMap.get(Servo.class, Constants.kVexMotor);
        Servo servo1 = hardwareMap.get(Servo.class, Constants.kFinger);
        DcMotor relicMotor = hardwareMap.get(DcMotor.class, Constants.kRelicMotor);
        relicMotor.setPower(0.0);
        relicMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        relicMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        relicMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();
//        pos = firstPos + 0.065; // 180
//        pos = firstPos + 0.065 / 2; // 90
        while (opModeIsActive()) {
            if(gamepad1.right_bumper)
                servo1.setPosition(0.2);
            else
                servo1.setPosition(1);

            if (gamepad1.a) {
                wantedState = States.Full;
            } else if (gamepad1.b){
                wantedState= States.HalfWay;
            }else if(gamepad1.x){
                wantedState = States.Protected;
            }



            if (currentState!=wantedState){
                if(!time) {
                    t.reset();
                    time = true;
                }

                if (t.milliseconds()<600){
                    switch (wantedState) {
                        case Protected:
                            if (currentState != States.Protected){
                                pos = 0.0;
                            }
                            break;

                        case HalfWay:
                            if (currentState == States.Protected){
                                pos = 1.0;
                            } else if(currentState == States.Full){
                                pos = 0.0;
                            }
                            break;

                        case Full:
                            if(currentState!=States.Full){
                                pos = 1.0;
                            }
                            break;
                    }
                } else {
                    switch (wantedState) {
                        case Protected:
                            pos = firstPos;
                            currentState = States.Protected;
                            time = false;
                            break;

                        case HalfWay:
                            pos = firstPos + (0.065/4); // 180
                            currentState = States.HalfWay;
                            time = false;
                            break;

                        case Full:
                            pos = firstPos + (0.065/2); // 180
                            currentState = States.Full;
                            time = false;
                            break;
                    }
                }
            }



            //servo.setPosition(pos);
            //telemetry.addData("pos", servo.getPosition());
            //relicMotor.setPower(gamepad1.right_stick_y);
            //telemetry.addData("Ticks", relicMotor.getCurrentPosition());
            //telemetry.addData("Draw",relicMotor.getCurrentDraw().doubleValue);
            telemetry.update();
        }

    }
}
