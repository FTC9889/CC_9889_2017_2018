package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by joshua9889 on 12/22/2017.
 * OpMode to reset the encoders
 */

@TeleOp
public class ResetEncoders extends Team9889Linear {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(this, false);
        Robot.zeroSensors();
    }
}
