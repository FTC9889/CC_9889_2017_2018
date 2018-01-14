package sample_camera_opmodes.VuforiaAndColor;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.Constants;
import com.team9889.lib.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import static com.team9889.lib.VuMark.SaveImage;

/**
 * Created by joshua9889 on 1/13/2018.
 */

@Autonomous(name = "Setting Up VuCam")
@Disabled
public class VuforiaAndColorSettingUp extends LinearOpMode {
    // Use your licence key in place of Constants.kVuforiaLicenceKey
    VuMark vuMark = new VuMark(Constants.kVuforiaLicenceKey);

    @Override
    public void runOpMode() throws InterruptedException {
        vuMark.setup(VuforiaLocalizer.CameraDirection.FRONT);

        vuMark.update();

        Bitmap bm = vuMark.getBm(20);

        // Scan area for red and blue pixels
        for (int x = 0; x < bm.getWidth()/5; x++) {
            for (int y = (bm.getHeight() / 4) + (bm.getHeight() / 2); y < bm.getHeight(); y++) {
                bm.setPixel(x, y, Color.GREEN);
            }
        }

        SaveImage(bm);
    }
}
