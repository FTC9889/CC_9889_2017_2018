package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by joshua9889 on 9/24/2017.
 */

@Autonomous(name = "Test Motors")
@Disabled
public class TestMotorEncoders extends LinearOpMode {
    DcMotor motor = null;
    DcMotor left = null;
    DcMotor right = null;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = this.hardwareMap.dcMotor.get("motor");
        left = this.hardwareMap.dcMotor.get("leftDrive");
        right = this.hardwareMap.dcMotor.get("rightDrive");

        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        this.waitForStart();

        while (opModeIsActive()){
            motor.setPower(0.2);
            telemetry.addData("Encoder counts", motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
