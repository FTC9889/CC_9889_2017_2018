package com.team9889.auto.modes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by Joshua on 8/4/2017.
 */
@Autonomous(name = "Test_Array")
@Disabled
public class Test_Array_Move extends Team9889LinearOpMode{

    //(distance, angle) positions
    protected int[][] LeftRightArray = {
        {10, 0},
        {10, 90}
    };

    public void runOpMode(){
        waitForTeamStart(this, true);

    }
}
