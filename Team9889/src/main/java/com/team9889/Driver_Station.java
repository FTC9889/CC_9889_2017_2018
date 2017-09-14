package com.team9889;

/**
 * Created by joshua9889 on 8/11/2017.
 */

public class Driver_Station{

    public Driver_Station(){}

    public Team9889LinearOpMode team9889LinearOpMode;

    public void init(Team9889LinearOpMode opMode){
        this.team9889LinearOpMode = opMode;
    }

    public boolean SlowDrivetrain(){
        return this.team9889LinearOpMode.gamepad1.left_trigger > 0.3;
    }

}
