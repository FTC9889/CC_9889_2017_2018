package com.team9889.Linear.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by Joshua on 8/4/2017.
 */

public class Superstructure {

    static Superstructure mInstance  = new Superstructure();

    private Team9889LinearOpMode mTeam9889LinearOpMode = null;

    public static Superstructure getInstance(){
        return mInstance;
    }

    public void Setup_Superstructure(Team9889LinearOpMode team9889LinearOpMode){

    }

    private Drive mDrive = new Drive();
    private Intake mIntake = new Intake();
    private Beacon mBeacon = new Beacon();
    private Flywheel mFlywheel = new Flywheel();

    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        mDrive.outputToTelemetry(opMode);
        mIntake.outputToTelemetry(opMode);
        mBeacon.outputToTelemetry(opMode);
        mFlywheel.outputToTelemetry(opMode);
    }

    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        boolean error = false;
        this.mTeam9889LinearOpMode = team9889LinearOpMode;

        if(!this.mDrive.init(mTeam9889LinearOpMode.hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Drive");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(!this.mBeacon.init(mTeam9889LinearOpMode.hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Beacon");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(!this.mIntake.init(mTeam9889LinearOpMode.hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Intake");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        if(!this.mFlywheel.init(mTeam9889LinearOpMode.hardwareMap, true)){
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


    public void stop() {
        mDrive.stop();
        mIntake.stop();
        mBeacon.stop();
        mFlywheel.stop();
    }


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
