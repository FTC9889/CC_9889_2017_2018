package com.team9889.Linear.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by Joshua on 8/4/2017.
 */

public class Superstructure extends Subsystem {

    static Superstructure mInstance  = new Superstructure();

    private Team9889LinearOpMode mTeam9889LinearOpMode = null;

    public static Superstructure getInstance(){
        return mInstance;
    }

    public void Setup_Superstructure(Team9889LinearOpMode team9889LinearOpMode){
        this.mTeam9889LinearOpMode = team9889LinearOpMode;
    }

    private Drive mDrive = new Drive();
    private Intake mIntake = new Intake();
    private Beacon mBeacon = new Beacon();
    private Flywheel mFlywheel = new Flywheel();

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        mDrive.outputToTelemetry(opMode);
        mIntake.outputToTelemetry(opMode);
        mBeacon.outputToTelemetry(opMode);
        mFlywheel.outputToTelemetry(opMode);
    }

    @Override
    public boolean init(HardwareMap hardwareMap, boolean auton) {
        boolean error = false;

        if(!this.mDrive.init(hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Drive");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(!this.mBeacon.init(hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Beacon");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(!this.mIntake.init(hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Intake");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(!this.mFlywheel.init(hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Flywheel");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(error){
            this.mTeam9889LinearOpMode.telemetry.addData("Error during Init","");
            this.mTeam9889LinearOpMode.telemetry.update();
        }else {
            this.mTeam9889LinearOpMode.telemetry.addData("No Errors Init","");
            this.mTeam9889LinearOpMode.telemetry.update();
        }

        return !error;
    }

    @Override
    public void stop() {
        mDrive.stop();
        mIntake.stop();
        mBeacon.stop();
        mFlywheel.stop();
    }

    @Override
    public void zeroSensors() {
        mDrive.zeroSensors();
        mIntake.zeroSensors();
        mBeacon.zeroSensors();
        mFlywheel.zeroSensors();
    }

    public Drive getDrive(){
        return mDrive;
    }

    public Intake getIntake(){
        return mIntake;
    }

    public Beacon getBeacon(){
        return mBeacon;
    }

    public Flywheel getFlywheel(){
        return mFlywheel;
    }
}
