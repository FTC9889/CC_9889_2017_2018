package com.team9889.auto.paths;

/**
 * Created by joshua9889 on 10/6/2017.
 *
 * Used for storing paths.
 */

public interface PathContainer {

    double[][] leftRightPositions();

    double[][] leftRightSpeeds();

    double[] gyroAngles();

    double[] timeStamps();

}
