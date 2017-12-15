package com.team9889;

/**
 * Class that includes all Buttons
 * Created by joshua9889 on 8/11/2017.
 */

public class Driver_Station{

    public Driver_Station(){}

    public Team9889Linear team9889Linear;

    public void init(Team9889Linear opMode){
        this.team9889Linear = opMode;
    }

    public boolean SlowDrivetrain(){
        return this.team9889Linear.gamepad1.left_trigger > 0.3;
    }


    public boolean level2() {
        return this.team9889Linear.gamepad1.b;
    }

    public boolean outtake() {
        return this.team9889Linear.gamepad1.right_bumper;
    }

    public boolean level4() {
        return this.team9889Linear.gamepad1.x;
    }

    public boolean intake() {
        return this.team9889Linear.gamepad1.a;
    }
}
