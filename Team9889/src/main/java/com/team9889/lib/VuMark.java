package com.team9889.lib;

import android.graphics.Bitmap;
import android.util.Log;

import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;
import com.vuforia.Image;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by joshua9889 on 10/14/2017.
 */

public class VuMark {
    public VuMark(){}

    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;
    private RelicRecoveryVuMark ouputVuMark = RelicRecoveryVuMark.UNKNOWN;
    ClosableVuforiaLocalizer vuforia;

    public void setup(VuforiaLocalizer.CameraDirection cameraDirection) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = Constants.kVuforiaLicenceKey;
        parameters.cameraDirection = cameraDirection;
        parameters.useExtendedTracking = false;
        vuforia = new ClosableVuforiaLocalizer(parameters);
        this.relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        this.relicTemplate = relicTrackables.get(0);
        this.ouputVuMark = RelicRecoveryVuMark.UNKNOWN;
        this.activateVuforia();
    }

    public void activateVuforia(){
        this.relicTrackables.activate();
    }

    public void disableVuforia(){
        this.relicTrackables.deactivate();
    }

    public void updateTarget(Team9889LinearOpMode team9889LinearOpMode){
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if(vuMark != RelicRecoveryVuMark.UNKNOWN)
            this.ouputVuMark = vuMark;
    }

    public RelicRecoveryVuMark getOuputVuMark(){
        return this.ouputVuMark;
    }

    public void closeVuforia() {
        this.vuforia.close();
    }

    public Bitmap readFrame() {
        VuforiaLocalizer.CloseableFrame frame;
        Image rgb = null;

        try {
            // grab the last frame pushed onto the queue
            frame = vuforia.getFrameQueue().take();
        } catch (InterruptedException e) {
            Log.d("Vuforia", "Problem taking frame off Vuforia queue");
            e.printStackTrace();
            return null;
        }

        // basically get the number of formats for this frame
        long numImages = frame.getNumImages();

        // set rgb object if one of the formats is RGB565
        for(int i = 0; i < numImages; i++) {
            if(frame.getImage(i).getFormat() == PIXEL_FORMAT.RGB565) {
                rgb = frame.getImage(i);
                break;
            }
        }

        if(rgb == null) {
            Log.d("Vuforia", "Image format not found");
            return null;
        }

        // create a new bitmap and copy the byte buffer returned by rgb.getPixels() to it
        Bitmap bm = Bitmap.createBitmap(rgb.getWidth(), rgb.getHeight(), Bitmap.Config.RGB_565);
        bm.copyPixelsFromBuffer(rgb.getPixels());

        return bm;
    }
}
