package com.team9889.Linear;

import com.team9889.Constants;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by joshua9889 on 9/10/2017.
 */

public class VuMark {
    public VuMark(){}

    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private VuforiaTrackables relicTrackables = null;
    private VuforiaTrackable relicTemplate = null;
    private VuforiaLocalizer.Parameters parameters = null;

    public void setup(Team9889LinearOpMode team9889LinearOpMode, VuforiaLocalizer.CameraDirection cameraDirection) {
        int cameraMonitorViewId = team9889LinearOpMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", team9889LinearOpMode.hardwareMap.appContext.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        //Set license key
        parameters.vuforiaLicenseKey = Constants.kVuforiaLicenceKey;

        parameters.cameraDirection = cameraDirection;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        this.relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        this.relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
    }

    public void activateVuforia(){
        relicTrackables.activate();
    }

    public void disableVuforia(){
        relicTrackables.deactivate();
    }

    public RelicRecoveryVuMark getTarget(Team9889LinearOpMode team9889LinearOpMode){
        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
        while (vuMark == RelicRecoveryVuMark.UNKNOWN && !team9889LinearOpMode.isStopRequested()){
            team9889LinearOpMode.telemetry.addData("Target", vuMark);
            team9889LinearOpMode.telemetry.update();
            team9889LinearOpMode.idle();
        }
        return vuMark;
    }
}
