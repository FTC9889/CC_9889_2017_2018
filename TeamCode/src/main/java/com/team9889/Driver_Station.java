package com.team9889;

/**
 * Class that includes all Buttons
 * Created by joshua9889 on 8/11/2017.
 */

public class Driver_Station{

    public Driver_Station(){}

    private Team9889Linear team9889Linear;

    public void init(Team9889Linear opMode){
        this.team9889Linear = opMode;
    }

    public boolean SlowDrivetrain(){
        return this.team9889Linear.gamepad1.left_trigger > 0.3;
    }

    boolean outtakeAndLevel2(){
        return this.team9889Linear.gamepad1.y;
    }

    boolean level2() {
        return this.team9889Linear.gamepad1.b;
    }

    boolean level4() {
        return this.team9889Linear.gamepad1.x;
    }

    boolean overTheBack(){
        return this.team9889Linear.gamepad1.dpad_up;
    }

    boolean release() {
        return this.team9889Linear.gamepad1.right_bumper;
    }

    boolean intake() {
        return this.team9889Linear.gamepad1.a;
    }

    boolean swivel(){
        return this.team9889Linear.gamepad2.right_trigger>0.3;
    }

    boolean leftRetract(){
        return this.team9889Linear.gamepad2.left_bumper;
    }

    boolean rightRetract(){
        return this.team9889Linear.gamepad2.b;
    }

    boolean retract(){
        return this.team9889Linear.gamepad2.right_bumper;
    }

    boolean outtake(){
        return this.team9889Linear.gamepad2.y;
    }
}
