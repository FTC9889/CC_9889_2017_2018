package com.team9889;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.lib.VuMark;
import com.team9889.subsystems.Superstructure;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by joshua on 4/17/17.
 */

public abstract class Team9889LinearOpMode extends LinearOpMode {

    public Superstructure mSuperstructure = Superstructure.getInstance();
    public VuMark vuMark = new VuMark();
    public Team9889LinearOpMode InternalopMode = null;
    private ElapsedTime period = new ElapsedTime();

    //Match settings
    public String alliance, frontBack;
    public boolean getPartnerGlyph;

    protected void waitForTeamStart(Team9889LinearOpMode opMode, boolean autonomous){
        this.InternalopMode = opMode;

        //this.mSuperstructure.init(this.InternalopMode, false);
        //this.updateTelemetry();

        if(autonomous){
            //Autonomous Settings
            this.InternalopMode.getAutonomousPrefs();
            this.InternalopMode.telemetry.addData("Ready to Start", "");
            this.InternalopMode.telemetry.addData("Alliance", alliance);
            this.InternalopMode.telemetry.addData("Front or Back", frontBack);
            this.InternalopMode.telemetry.addData("Pickup", getPartnerGlyph);
            this.InternalopMode.telemetry.update();

            //Vuforia
            //Uses the camera on the screen side
            this.vuMark.setup(this.InternalopMode, VuforiaLocalizer.CameraDirection.FRONT);
            while(!isStarted()){
                this.vuMark.updateTarget(this);
                idle();
            }
        }


        //Wait for DS start
        this.InternalopMode.waitForStart();

        if(autonomous)
            this.vuMark.disableVuforia();
    }

    /**
     * Run this to update the Default Telemetry
     */
    public void updateTelemetry(){
        this.InternalopMode.telemetry.addData("Runtime> ", this.InternalopMode.getRuntime());
        this.mSuperstructure.outputToTelemetry(this.InternalopMode);
        this.InternalopMode.telemetry.update();
    }

    //Final Action to be run
    protected void finalAction(){
        try {
            this.mSuperstructure.stop();
        } catch (Exception e){}

        this.InternalopMode.requestOpModeStop();
    }

    //Built-in function by FIRST. Put in all loops
    protected void waitForTick(long periodMs) {
        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    private void getAutonomousPrefs() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(hardwareMap.appContext);
        alliance = preferences.getString("AllianceColor", "error");
        frontBack = preferences.getString("FrontBack", "error");
        getPartnerGlyph = preferences.getBoolean("PickupAllianceGlyph", false);
    }
}
