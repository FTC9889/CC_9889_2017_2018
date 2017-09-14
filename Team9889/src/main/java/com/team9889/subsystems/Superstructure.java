package com.team9889.subsystems;

import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.Navigation;

/**
 * Created by Joshua on 8/4/2017.
 */

public class Superstructure{

    private static Superstructure mInstance  = new Superstructure();

    private Team9889LinearOpMode mTeam9889LinearOpMode = null;

    public static Superstructure getInstance(){
        return mInstance;
    }

    private Drive mDrive = new Drive();
    private Navigation navigation = new Navigation(mDrive);

    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        mDrive.outputToTelemetry(opMode);
    }

    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        boolean error = false;
        this.mTeam9889LinearOpMode = team9889LinearOpMode;

        if(!this.mDrive.init(mTeam9889LinearOpMode.hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Drive");
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
    }


    public void zeroSensors() {
        mDrive.zeroSensors();
    }

    public Drive getDrive(){
        return mDrive;
    }

}
