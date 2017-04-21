package com.team9889.ftc2017.auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.ftc2017.Team9889LinearOpMode;
import com.team9889.ftc2017.auto.actions.Action;
import com.team9889.ftc2017.subsystems.Beacon;
import com.team9889.ftc2017.subsystems.Drive;
import com.team9889.ftc2017.subsystems.Flywheel;
import com.team9889.ftc2017.subsystems.Intake;

/**
 * Created by Joshua H on 4/10/2017.
 *
 * An abstract class that is the basis of the robot's autonomous routines. This
 * is implemented in auto.modes (which are routines that do actions).
 */
public abstract class AutoModeBase extends Team9889LinearOpMode{

    /**
     *
     * @param action
     * @param opMode just type in "this"
     */
    public void runAction(Action action, LinearOpMode opMode){
        action.start();
        while (!action.isFinished() && opMode.opModeIsActive()){
            action.update(opMode);
        }
        action.done();
    }

}
