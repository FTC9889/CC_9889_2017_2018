package com.team9889.Linear;

import com.team9889.Constants;

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
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        team9889LinearOpMode.telemetry.addData(">", "Vuforia Finished");
        team9889LinearOpMode.telemetry.update();
    }

    public void activateVuforia(){
        relicTrackables.activate();
    }

    public void disableVuforia(){
        relicTrackables.deactivate();
    }

    public void updateTarget(Team9889LinearOpMode team9889LinearOpMode){
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        if(vuMark != RelicRecoveryVuMark.UNKNOWN)
            ouputVuMark = vuMark;

        team9889LinearOpMode.telemetry.addData("VuMark", "%s visible", vuMark);
        team9889LinearOpMode.telemetry.update();
    }

    public RelicRecoveryVuMark getOuputVuMark(){
        return ouputVuMark;
    }
}
