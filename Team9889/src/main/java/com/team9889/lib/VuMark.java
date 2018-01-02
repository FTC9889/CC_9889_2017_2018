package com.team9889.lib;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Team9889Linear;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by joshua9889 on 10/14/2017.
 */

public class VuMark {

    // Vuforia THings
    private String vuforiaLicenseKey ="";
    private VuforiaLocalizer vuforia;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;
    private RelicRecoveryVuMark ouputVuMark = RelicRecoveryVuMark.UNKNOWN;

    // Bitmap things
    private Image img = null;
    public Bitmap bm = null;

    /**
     * @param licenseKey Vuforia license key
     */
    public VuMark(String licenseKey){
        this.vuforiaLicenseKey = licenseKey;
    }

    /**
     * @param cameraDirection What camera to use
     */
    public void setup(VuforiaLocalizer.CameraDirection cameraDirection) {
        VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
        params.vuforiaLicenseKey = Constants.kVuforiaLicenceKey;
        params.cameraDirection = cameraDirection;

        this.vuforia = ClassFactory.createVuforiaLocalizer(params);
        vuforia.setFrameQueueCapacity(1);
        Vuforia.setFrameFormat(PIXEL_FORMAT.RGB565, true);

        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");
        this.activateVuforia();
    }

    /**
     * Activate Vuforia Tracker
     */
    public void activateVuforia(){
        this.relicTrackables.activate();
    }

    /**
     * Disable Vuforia Tracker
     */
    public void disableVuforia(){
        this.relicTrackables.deactivate();
    }

    /**
     * @return output vumark
     */
    public RelicRecoveryVuMark getOuputVuMark(){
        return this.ouputVuMark;
    }

    /**
     * Use this method to update the current vumark
     * @param team9889Linear Current Team9889Linear Opmode.
     */
    public void update(Team9889Linear team9889Linear){
        // VuMark Update
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if(vuMark != RelicRecoveryVuMark.UNKNOWN)
            this.ouputVuMark = vuMark;
    }

    // Used to convert a Vuforia Frame into a Image in our favorite format
    // Used for Camera
    @Nullable
    private static Image getImageFromFrame(VuforiaLocalizer.CloseableFrame frame, int format) {
        long numImgs = frame.getNumImages();
        for (int i = 0; i < numImgs; i++) {
            if (frame.getImage(i).getFormat() == format) {
                return frame.getImage(i);
            }//if
        }//for

        return null;
    }

    // Get Bitmap from vuforia
    public Bitmap getBm(){
        try {
            img = getImageFromFrame(vuforia.getFrameQueue().take(), PIXEL_FORMAT.RGB565);
            Bitmap bm = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.RGB_565);
            bm.copyPixelsFromBuffer(img.getPixels());

            return bm;
        } catch(Exception e){
            RobotLog.a("Problem with getBm");
            return null;
        }
    }

    public int red(int pixel) {
        return (pixel >> 16) & 0xff;
    }

    public int green(int pixel) {
        return (pixel >> 8) & 0xff;
    }

    public int blue(int pixel) {
        return pixel & 0xff;
    }

    public int gray(int pixel) {
        return (red(pixel) + green(pixel) + blue(pixel));
    }
}
