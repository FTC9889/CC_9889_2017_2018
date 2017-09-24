package com.team9889.subsystems;

import com.team9889.Team9889LinearOpMode;
import com.team9889.lib.Navigation;

/**
 * Superstructure combines all of subsystems into one importable class.
 * Used for init robot hardware and vision.
 * Created by Joshua on 8/4/2017.
 */

public class Superstructure{

    private static Superstructure mInstance  = new Superstructure();

    //Internal Opmode to output telemetry.
    private Team9889LinearOpMode mTeam9889LinearOpMode = null;

    public static Superstructure getInstance(){
        return mInstance;
    }

    private Drive mDrive = new Drive();

    /**
     * Add each subsystem's outputToTelemetry in this method.
     * @param opMode Current Team9889LinearOpMode
     */
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        mDrive.outputToTelemetry(opMode);
    }

    /**
     * Init all subsytems in this method.
     * @param team9889LinearOpMode The OpMode Object
     * @param auton If the OpMode is for autonomous mode or not.
     * @return If all subsystems init properly, return true.
     */
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        boolean error = false;
        this.mTeam9889LinearOpMode = team9889LinearOpMode;

        //Structure all inits like this.
        if(!this.mDrive.init(mTeam9889LinearOpMode.hardwareMap, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Drive");
            this.mTeam9889LinearOpMode.telemetry.update();
            error = true;
        }

        //Code to check for errors.
        if(error){
            this.mTeam9889LinearOpMode.telemetry.addData("Error during Init","");
            this.mTeam9889LinearOpMode.telemetry.update();
        }else {
            this.mTeam9889LinearOpMode.telemetry.addData("No Errors Init","");
            this.mTeam9889LinearOpMode.telemetry.update();
        }

        return !error;
    }


    /**
     * Stop all subsystems with this method.
     */
    public void stop() {
        try {
            mDrive.stop();
        } catch (Exception e){}
    }


    /**
     * Reset all subsystems with this method.
     */
    public void zeroSensors() {
        try {
            mDrive.zeroSensors();
        } catch (Exception e){}
    }

    /**
     * Get Drivetrain
     * @return mDrive
     */
    public Drive getDrive(){
        try {
            return mDrive;
        } catch (Exception e){
            return null;
        }
    }

}
