package com.team9889.Linear.lib;

import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by Joshua H on 6/20/2017.
 */

public class DetectBeaconColor {
    public void init(HardwareMap hardwareMap) {
    }

    static public int red(int pixel) {
        return (pixel >> 16) & 0xff;
    }

    static public int green(int pixel) {
        return (pixel >> 8) & 0xff;
    }

    static public int blue(int pixel) {
        return pixel & 0xff;
    }


}
