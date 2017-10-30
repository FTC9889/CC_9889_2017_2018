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

    private List<Subsystem> subsystems;
    
    /**
     * Add each subsystem's outputToTelemetry in this method.
     * @param opMode Current Team9889LinearOpMode
     */
    public void outputToTelemetry(Team9889LinearOpMode opMode) {
        for (int i=0;i<subsystems.size();i++){
            subsystems.get(i).outputToTelemetry(opMode);
        }
        //mDrive.outputToTelemetry(opMode);
        //mJewel.outputToTelemetry(opMode);
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

        subsystems.add(mDrive);
        subsystems.add(mJewel);


        for (int i=0;i<subsystems.size();i++){
            if(!subsystems.get(i).init(mTeam9889LinearOpMode, auton)){
                this.mTeam9889LinearOpMode.telemetry.addData("Error", i);
                this.mTeam9889LinearOpMode.telemetry.update();
                error = true;
            }
        }

//        Structure all inits like this.
//        if(!this.mDrive.init(mTeam9889LinearOpMode, true)){
//            this.mTeam9889LinearOpMode.telemetry.addData("Error", " MRDrive");
//            this.mTeam9889LinearOpMode.telemetry.update();
//            error = true;
//        }
//
//        Structure all inits like this.
//        if(!this.mJewel.init(mTeam9889LinearOpMode, true)){
//            this.mTeam9889LinearOpMode.telemetry.addData("Error", " Jewel");
//            this.mTeam9889LinearOpMode.telemetry.update();
//            error = true;
//        }

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
            for(int i=0;i<subsystems.size();i++) {
                subsystems.get(i).stop();
            }

//            mDrive.stop();
//            mJewel.stop();
        } catch (Exception e){}
    }


    /**
     * Reset all subsystems with this method.
     */
    public void zeroSensors() {
        try {
            for(int i=0;i<subsystems.size();i++){
                subsystems.get(i).stop();
            }
//            mDrive.zeroSensors();
//            mJewel.zeroSensors();
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

}
