package com.team9889.test.Subsystem;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Constants;
import com.team9889.Team9889Linear;
import com.team9889.lib.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static com.team9889.lib.VuMark.*;

/**
 * Created by joshua9889 on 12/21/2017.
 */
@TeleOp
@Disabled
public class TestingNewVumarkCode extends Team9889Linear {

    private VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);
    private long redVotes = 0;
    private long blueVotes = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        // Setup Vuforia
        vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);
        ElapsedTime t = new ElapsedTime();

        while(isInInitLoop()) {
            // Update VuMark
            vuMark.update();

            // Value of all pixels
            int redValue = 0;
            int blueValue = 0;

            // Get current bitmap from vuforia
            Bitmap bm = vuMark.getBm(20);

            if(bm != null){
                // Scan area for red and blue pixels
                for (int x = 0; x < bm.getWidth()/5 && isInInitLoop(); x++) {
                    for (int y = (bm.getHeight()/4)+(bm.getHeight()/2); y < bm.getHeight() && isInInitLoop(); y++) {
                        int pixel = bm.getPixel(x,y);
                        redValue += red(pixel);
                        blueValue += blue(pixel);

                        if(redValue>blueValue)
                            redVotes++;
                        else if(blueValue>redValue)
                            blueVotes++;
                        idle();
                    }
                }
                bm.recycle();
            }


            // Voting system
            if(redVotes>blueVotes)
                jewel_Color = JewelColor.Red;
            else if(blueVotes>redVotes)
                jewel_Color = JewelColor.Blue;

            // Cut off
            if (redVotes>300) {
                redVotes = 50;
                blueVotes = 0;
            } else if(blueVotes > 300){
                redVotes = 0;
                blueVotes = 50;
            }

            // Calculate FPS
            double fps = 1/t.seconds();
            t.reset();

            // Output Telemetry
            telemetry.addData("FPS", fps);
            telemetry.addData("VuMark", vuMark.getOuputVuMark().toString());
            if(jewel_Color != null)
                telemetry.addData("Color", jewel_Color.toString());
            telemetry.addData("Red Votes", redVotes);
            telemetry.addData("Blue Votes", blueVotes);
            telemetry.update();
            idle();
        }


        // Rotate and Save Bitmap to "saved_images"
        Matrix matrix = new Matrix();
        matrix.postRotate(-90);
        Bitmap scaledBitmap = vuMark.getBm(20);
        Bitmap rotatedBitmap = Bitmap.createBitmap(scaledBitmap , 0, 0, scaledBitmap .getWidth(), scaledBitmap .getHeight(), matrix, true);
        SaveImage(rotatedBitmap);
        scaledBitmap.recycle();
        rotatedBitmap.recycle();
    }


}