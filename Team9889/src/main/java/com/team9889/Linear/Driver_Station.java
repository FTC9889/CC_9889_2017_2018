package com.team9889.Linear;

/**
 * Created by Joshua on 8/11/2017.
 */

public class Driver_Station{

    public Driver_Station(){}

    public Team9889LinearOpMode team9889LinearOpMode;

    public void init(Team9889LinearOpMode opMode){
        this.team9889LinearOpMode = opMode;
    }

    public boolean ShootBoolean(){
        return this.team9889LinearOpMode.gamepad1.dpad_up;
    }

    public boolean SlowDrivetrain(){
        return this.team9889LinearOpMode.gamepad1.left_trigger > 0.3;
    }

    public boolean ClearParticlesFromFlywheel(){
        return this.team9889LinearOpMode.gamepad2.a;
    }

    public boolean Intake(){
        return this.team9889LinearOpMode.gamepad1.right_bumper;
    }

    public boolean Outtake(){
        return this.team9889LinearOpMode.gamepad1.right_trigger > 0.3;
    }

    public boolean BeaconDeploy(){
        return false;
    }
}
