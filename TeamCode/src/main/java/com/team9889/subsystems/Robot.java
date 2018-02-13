package com.team9889.subsystems;

import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Team9889Linear;

import java.util.Arrays;
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

    private Drive mDrive = new Drive(); //Drivetrain
    private Jewel mJewel = new Jewel(); //Jewel Mech
    private GlyphLypht mLift = new GlyphLypht(); // Glyph Lift
    private Intake mIntake = new Intake(); // Intake for glyph

    private List<Subsystem> subsystems = Arrays.asList(
            mDrive, mJewel, mLift, mIntake);

    /**
     * @param opMode Current Team9889Linear
     */
    public void outputToTelemetry(Team9889Linear opMode) {
        for (Subsystem subsystem:subsystems)
            subsystem.outputToTelemetry(opMode.telemetry);
    }

    /**
     * Init all subsytems in this method.
     * @param team9889Linear The OpMode Object
     * @param autonomous If the OpMode is for autonomous mode or not.
     */
    public void init(Team9889Linear team9889Linear, boolean autonomous) {
        boolean error = false;

        for (Subsystem subsystem:subsystems){
            if(!subsystem.init(team9889Linear, autonomous)){
                RobotLog.a("Error at:" + subsystem.toString());
                error = true;
            }
        }

        // Code to check for errors.
        if(error){
            team9889Linear.telemetry.addData("Error during Init","");
            team9889Linear.telemetry.update();
        }else {
            team9889Linear.telemetry.addData("No Errors Init","");
            team9889Linear.telemetry.update();
        }
    }


    /**
     * Stop all subsystems with this method.
     */
    public void stop() {
        try {
            for (Subsystem subsystem:subsystems)
                subsystem.stop();
        } catch (Exception e){}
    }


    /**
     * Reset all subsystems with this method.
     */
    public void zeroSensors() {
        try {
            for (Subsystem subsystem:subsystems)
                subsystem.zeroSensors();
        } catch (Exception e){}
    }

    /**
     * Get Drivetrain object
     * @return mDrive
     */
    public Drive getDrive(){
        return mDrive;
    }

    /**
     * Get Jewel Mech object
     * @return mJewel
     */
    public Jewel getJewel() {
        return mJewel;
    }

    /**
     * Get Glyph Lift object
     * @return mLift
     */
    public GlyphLypht getLift() {
        return mLift;
    }

    /**
     * Get Glyph Lift object
     * @return mIntake
     */
    public Intake getIntake() {
        return mIntake;
    }
}
