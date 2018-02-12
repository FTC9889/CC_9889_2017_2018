package com.team9889.auto.paths;

/**
 * Created by joshua9889 on 10/6/2017.
 */

public class RedFrontCryptoboxFront implements PathContainer {
    @Override
    public double[][] leftRightPositions() {
        double[][] positions = {
                {0.0, 0.0},
                {10, 10}
        };
        return positions;
    }

    @Override
    public double[][] leftRightSpeeds() {
        double[][] powers = {
                {0.0, 0.0},
                {0.75, 0.75}
        };
        return powers;
    }

    @Override
    public double[] gyroAngles() {
        double[] angles = {
                0.0,
                0.0
        };
        return angles;
    }

    @Override
    public double[] timeStamps() {
        double[] times = {
                0,
                3
        };
        return times;
    }
}
