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
 * Run updateTarget in a while(!isStarted()) before start
 *
 * Use getOutputVuMark to get the image scanned in updateTarget.
 *
 */

public class VuMark {
    public VuMark(){}

    private VuforiaLocalizer vuforia = null;
    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;
    private VuforiaLocalizer.Parameters parameters = null;
    private RelicRecoveryVuMark ouputVuMark = RelicRecoveryVuMark.UNKNOWN;

    public void setup(Team9889LinearOpMode team9889LinearOpMode, VuforiaLocalizer.CameraDirection cameraDirection) {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = Constants.kVuforiaLicenceKey;
        parameters.cameraDirection = cameraDirection;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        this.relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
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

        team9889LinearOpMode.telemetry.addData("VuMark", "%s visible", vuMark);
        team9889LinearOpMode.telemetry.update();
    }

    public RelicRecoveryVuMark getOuputVuMark(){
        return this.ouputVuMark;
    }
}
