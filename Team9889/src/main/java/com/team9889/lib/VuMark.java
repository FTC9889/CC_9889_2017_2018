package com.team9889.lib;

import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by joshua9889 on 9/10/2017.
 *
 * Class used for VuMark.
 * Run setup to setup vuforia.
 * Run activateVuforia to activate Vuforia.
 *
 * Run updateTarget in a while(!isStarted()) loop before start
 *
 * Use getOutputVuMark to get the image scanned in updateTarget.
 *
 */

public class VuMark {
    public VuMark(){}

    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;
    private RelicRecoveryVuMark ouputVuMark = RelicRecoveryVuMark.UNKNOWN;

    public void setup(VuforiaLocalizer.CameraDirection cameraDirection) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = Constants.kVuforiaLicenceKey;
        parameters.cameraDirection = cameraDirection;
        parameters.useExtendedTracking = false;
        VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        this.relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        this.relicTemplate = relicTrackables.get(0);
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
}
