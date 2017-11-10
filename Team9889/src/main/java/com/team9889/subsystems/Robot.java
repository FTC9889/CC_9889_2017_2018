package com.team9889.subsystems;

import com.team9889.Team9889LinearOpMode;

import java.util.List;

/**
 * Robot combines all of subsystems into one importable class.
 * Used for init all robot hardware.
 * Created by Joshua on 8/4/2017.
 */

public class Robot {

    private static Robot mInstance  = new Robot();
    public static Robot getInstance(){
        return mInstance;
    }

    //Internal Opmode to output telemetry.
    private Team9889LinearOpMode mTeam9889LinearOpMode = null;

    private Drive mDrive = new Drive(); //Drivetrain
    private Jewel mJewel = new Jewel(); //Jewel Mech
    private GlyphLypht mLift = new GlyphLypht(); // Glyph Lift
    private Intake mIntake = new Intake(); // Intake for glyph

    /**
     * Add each subsystem's outputToTelemetry in this method.
     * @param opMode Current Team9889LinearOpMode
     */
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        mDrive.outputToTelemetry(opMode);
        mJewel.outputToTelemetry(opMode);
        mLift.outputToTelemetry(opMode);
        mIntake.outputToTelemetry(opMode);
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

//        Structure all inits like this.
        if(!this.mDrive.init(mTeam9889LinearOpMode, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Drive");
            error = true;
        }

        if(!this.mJewel.init(mTeam9889LinearOpMode, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Jewel");
            error = true;
        }

        if(!this.mLift.init(mTeam9889LinearOpMode, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Lift");
            error = true;
        }

        if(!this.mIntake.init(mTeam9889LinearOpMode, true)){
            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Intake");
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
            mJewel.stop();
            mLift.stop();
            mIntake.stop();
        } catch (Exception e){}
    }


    /**
     * Reset all subsystems with this method.
     */
    public void zeroSensors() {
        try {
            mDrive.zeroSensors();
            mJewel.zeroSensors();
            mLift.zeroSensors();
            mIntake.zeroSensors();
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

    /**
     * Get Jewel Mech
     * @return mJewel
     */
    public Jewel getJewel() {
        try {
            return mJewel;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * @return mLift
     */
    public GlyphLypht getLift() {
        try {
            return mLift;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * @return mIntake
     */
    public Intake getIntake() {
        try {
            return mIntake;
        } catch (Exception e){
            return null;
        }
    }
}
