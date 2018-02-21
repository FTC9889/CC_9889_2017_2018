package com.team9889.test.Autonomus.Control.paths;

/**
 * Created by joshua9889 on 10/6/2017.
 *
 * Used for storing paths.
 */

public interface PathContainer {

    double[] x();
    double[] y();
    Alerts[] alerts();
    double lastAngle();

    enum Alerts{
        None,
        StorePreload,
        DeployToIntake,
        DelpoyToFirstLevel
    }

    enum Drive{
        Foward,
        Backward
    }

    enum Turn{
        CW,
        CCW
    }
}
