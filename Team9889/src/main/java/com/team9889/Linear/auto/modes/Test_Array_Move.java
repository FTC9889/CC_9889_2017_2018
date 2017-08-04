package com.team9889.Linear.auto.modes;

import com.team9889.Linear.Team9889LinearOpMode;

/**
 * Created by Joshua on 8/4/2017.
 */

public class Test_Array_Move extends Team9889LinearOpMode{

    //(distance, angle) positions
    protected int[][] LeftRightArray = {
        {10, 0},
        {10, 90}
    };

    public void runOpMode(){
        waitForTeamStart(this);

    }
}
