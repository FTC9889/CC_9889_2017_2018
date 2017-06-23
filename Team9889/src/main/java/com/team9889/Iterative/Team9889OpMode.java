package com.team9889.Iterative;

import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Constants;
import com.team9889.Linear.lib.Camera_Flash;
import com.team9889.Linear.subsystems.*;
import for_camera_opmodes.OpModeCamera;

/**
 * Created by joshua on 6/21/17.
 */

public abstract class Team9889OpMode extends OpModeCamera {
    public Drive mDrive = Drive.getInstance();
    public Beacon mBeacon = Beacon.getInstance();
    public Intake mIntake = Intake.getInstance();
    public Flywheel mFlywheel = Flywheel.getInstance();
    public Camera_Flash camera_flash = new Camera_Flash();

    //Camera
    int ds2 = 2;  // additional downsampling of the image
    private int looped = 0;
    private long lastLoopTime = 0;
    // set to 1 to disable further downsampling

    public void init() {
        setCameraDownsampling(8);

        camera_flash.On(true);
        telemetry.addData("Error", " Drive");
        telemetry.update();
        //Init Hardware
        mDrive.init(hardwareMap, true);

        telemetry.addData("Error", " Beacon");
        telemetry.update();
        mBeacon.init(hardwareMap, true);


        telemetry.addData("Error", " Intake");
        telemetry.update();
        mIntake.init(hardwareMap, true);

        telemetry.addData("Error", " Flywheel");
        telemetry.update();
        mFlywheel.init(hardwareMap, true);

        telemetry.addData("No Errors Init","");
        telemetry.update();

        //Zero Sensors
        mDrive.zeroSensors();
        mBeacon.zeroSensors();
        mFlywheel.zeroSensors();
        mIntake.zeroSensors();

        //Set init state of robot
        mDrive.DriveControlState(Drive.DriveControlState.POWER);
        mDrive.DriveZeroPowerState(Drive.DriveZeroPower.FLOAT);

        mFlywheel.WantedState(Flywheel.WantedState.OFF);

        mIntake.WantedState(Intake.WantedState.WANTS_STOP);

        mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);

        super.init();
    }

    public void stop() {
        try {
            mDrive.stop();
            mFlywheel.stop();
            mBeacon.stop();
            mIntake.stop();
        } catch (Exception e){
            RobotLog.a("Error Stop method" + Constants.OpMode);
        }

        stopCamera();

        camera_flash.ReleaseCamera();

        super.stop();
    }
}
