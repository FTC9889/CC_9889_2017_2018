package com.team9889;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.Linear.VuMark;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by joshua9889 on 9/10/2017.
 */

@Autonomous(name = "test Vuforia")
public class TestVuforia extends Team9889LinearOpMode{
    VuMark vuMark = new VuMark();

    @Override
    public void runOpMode() throws InterruptedException {
        vuMark.setup(this, VuforiaLocalizer.CameraDirection.BACK);
        waitForStart();
        vuMark.activateVuforia();
        telemetry.addData("Name", vuMark.getTarget(this));
        telemetry.update();
        waitForTick(5000);
    }
}
