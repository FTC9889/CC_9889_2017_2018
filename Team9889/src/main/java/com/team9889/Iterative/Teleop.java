package com.team9889.Iterative;

/**
 * Created by Joshua on 7/30/2017.
 */

public class Teleop extends Team9889OpMode {
    @Override
    public void loop(){
        DriveSteerThread.run();
    }
}
