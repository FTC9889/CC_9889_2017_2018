package com.team9889;

/**
 * Class that includes all Buttons
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


    public boolean level2() {
        return this.team9889LinearOpMode.gamepad1.b;
    }

    public boolean outtake() {
        return this.team9889LinearOpMode.gamepad1.right_trigger > 0.4;
    }

    public boolean level3() {
        return this.team9889LinearOpMode.gamepad1.y;
    }

    public boolean level4() {
        return this.team9889LinearOpMode.gamepad1.x;
    }

    public boolean intake() {
        return this.team9889LinearOpMode.gamepad1.a;
    }
}
