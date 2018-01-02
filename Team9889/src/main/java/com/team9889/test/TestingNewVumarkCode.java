package com.team9889.test;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Team9889Linear;
import com.team9889.lib.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static camera_opmodes.LinearOpModeCamera.blue;
import static camera_opmodes.LinearOpModeCamera.green;
import static camera_opmodes.LinearOpModeCamera.red;

/**
 * Created by joshua9889 on 12/21/2017.
 */
@TeleOp
//@Disabled
public class TestingNewVumarkCode extends Team9889Linear {

    VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);
    int redVotes = 0;
    int blueVotes = 0;
    Bitmap bm = null;

    @Override
    public void runOpMode() throws InterruptedException {
        vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);

        while (!isStarted() && !isStopRequested()){
            vuMark.update(this);

            int redValue = 0;
            int blueValue = 0;
            int greenValue = 0;

            try {
                bm = vuMark.getBm();
            } catch (Exception e){
                RobotLog.a("Problem getting Bm");
            }

            RobotLog.a("H:" + String.valueOf(bm.getHeight()));
            RobotLog.a("W:" + String.valueOf(bm.getWidth()));

            int w = bm.getHeight();
            int h = bm.getWidth();

            try{
//                for (int x =0; x < w/4; x++) {
                    for (int y = 0; y < w/2; y++) {
                        int pixel = bm.getPixel(h/2, y);
                        redValue += red(pixel);
                        blueValue += blue(pixel);
                        greenValue += green(pixel);
                    //}
                }
            } catch (Exception e){
                RobotLog.a("Problem in for loops");
            }


            // Single votes
//            if (redValue > blueValue)
//                redVotes++;
//            else if(redValue < blueValue)
//                blueVotes++;
//
//            if(redVotes > 100){
//                blueVotes = 0;
//                redVotes = 1;
//            } else if(blueVotes > 100){
//                blueVotes = 1;
//                redVotes = 0;
//            }

            telemetry.addData("VuMark", vuMark.getOuputVuMark().toString());
            telemetry.addData("red", redValue);
            telemetry.addData("blue", blueValue);
            telemetry.addData("green", greenValue);
            telemetry.update();
        }

        waitForStart();

    }
}
