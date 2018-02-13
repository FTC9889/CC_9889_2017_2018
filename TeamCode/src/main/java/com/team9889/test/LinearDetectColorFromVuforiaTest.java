package com.team9889.test;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.Constants;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import static com.team9889.lib.VuMark.blue;
import static com.team9889.lib.VuMark.red;

/**
 * Test COde to extract a bitmap from vuforia
 * Created by joshua9889 on 12/21/2017.
 */

@TeleOp
@Disabled
public class LinearDetectColorFromVuforiaTest extends LinearOpMode{
    VuforiaLocalizer vuforia;

    @Override
    public void runOpMode() throws InterruptedException {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters();
        params.vuforiaLicenseKey = Constants.kVuforiaLicenceKey;
        params.cameraDirection = VuforiaLocalizer.CameraDirection.FRONT;

        this.vuforia = ClassFactory.createVuforiaLocalizer(params);
        vuforia.setFrameQueueCapacity(1);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);

        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        telemetry.update();
        waitForStart();
        relicTrackables.activate();
        int redVotes = 0;
        int blueVotes = 0;

        while (opModeIsActive()){
            Image img = getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565);
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);

            if(img == null){
                telemetry.addData("Cry","");
            } else {
                telemetry.addData("Be happy", "");
                telemetry.addData("VuMark", vuMark.toString());
                Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
                bm.copyPixelsFromBuffer(img.getPixels());

                int redValue = 0;
                int blueValue = 0;

                for (int x =0; x < bm.getWidth()/4; x++) {
                    for (int y = 0; y < bm.getHeight()/6; y++) {
                        int pixel = bm.getPixel(x, y);
                        redValue += red(pixel);
                        blueValue += blue(pixel);
                    }
                }

                // Single votes
                if (redValue > blueValue)
                    redVotes++;
                else
                    blueVotes++;

                telemetry.addData("red", redVotes);
                telemetry.addData("blue", blueVotes);
            }


            telemetry.update();
        }

    }

    @Nullable
    public static Image getImageFromFrame(VuforiaLocalizer.CloseableFrame frame, int format) {

        long numImgs = frame.getNumImages();
        for (int i = 0; i < numImgs; i++) {
            if (frame.getImage(i).getFormat() == format) {
                return frame.getImage(i);
            }//if
        }//for

        return null;
    }
}
