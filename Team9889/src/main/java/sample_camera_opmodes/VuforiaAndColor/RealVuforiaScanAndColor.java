package sample_camera_opmodes.VuforiaAndColor;

import android.graphics.Bitmap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Constants;
import com.team9889.lib.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static com.team9889.lib.VuMark.blue;
import static com.team9889.lib.VuMark.red;

/**
 * Created by joshua9889 on 1/13/2018.
 */

@Autonomous
@Disabled
public class RealVuforiaScanAndColor extends LinearOpMode {
    VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);
    private ElapsedTime t = new ElapsedTime();
    public int redVotes = 0;
    public int blueVotes = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);

        while(!isStarted() && !isStopRequested()) {
            // Update VuMark
            vuMark.update();

            // Value of all pixels
            int redValue = 0;
            int blueValue = 0;

            // Get current bitmap from Vuforia
            Bitmap bm = vuMark.getBm(20);

            if(bm != null){
                // Scan area for red and blue pixels

                // Put the parameters from VuforiaAndColorSettingUp loops here:
                for (int x = 0; x < bm.getWidth()/5 && !isStarted() && !isStopRequested(); x++) {
                    for (int y = (bm.getHeight()/4)+(bm.getHeight()/2); y < bm.getHeight() && !isStarted() && !isStopRequested(); y++) {
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
            telemetry.addData("VuMark", vuMark.getOuputVuMark());

            if(redVotes>blueVotes)
                telemetry.addData("Color", "Red");
            else
                telemetry.addData("Color", "Blue");

            telemetry.addData("Red Votes", redVotes);
            telemetry.addData("Blue Votes", blueVotes);
            telemetry.update();
            idle();
        }
    }
}
