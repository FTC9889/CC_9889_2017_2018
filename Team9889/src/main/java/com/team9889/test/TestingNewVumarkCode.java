package com.team9889.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.Constants;
import com.team9889.Team9889Linear;
import com.team9889.lib.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by joshua9889 on 12/21/2017.
 */
@TeleOp
@Disabled
public class TestingNewVumarkCode extends Team9889Linear {
    VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);
    int redVotes = 0;
    int blueVotes = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);

        waitForStart();

        while (opModeIsActive()){
            vuMark.update(this);

            int redValue = 0;
            int blueValue = 0;

            try{
                for (int x =0; x < vuMark.getBm().getWidth()/4; x++) {
                    for (int y = 0; y < vuMark.getBm().getHeight()/6; y++) {
                        int pixel = vuMark.getBm().getPixel(x, y);
                        redValue += vuMark.red(pixel);
                        blueValue += vuMark.blue(pixel);
                    }
                }
            } catch (Exception e){}


            // Single votes
            if (redValue > blueValue)
                redVotes++;
            else
                blueVotes++;

            telemetry.addData("VuMark", vuMark.getOuputVuMark().toString());
            telemetry.addData("red", redVotes);
            telemetry.addData("blue", blueVotes);
            telemetry.update();
        }
    }
}
