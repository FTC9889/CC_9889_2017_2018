package com.team9889.test.Autonomus.Control.paths;

/**
 * Created by joshua9889 on 10/6/2017.
 */

public class RedFrontCryptoboxFront implements PathContainer {

    @Override
    public double[] x() {
        return new double[]{
                120,
                102,
                85
        };
    }

    @Override
    public double[] y() {
        return new double[]{
                96,
                96,
                80
        };
    }

    @Override
    public Alerts[] alerts() {
        return new Alerts[]{
                Alerts.None,
                Alerts.StorePreload,
                Alerts.None
        };
    }

    @Override
    public double lastAngle() {
        double last_x = x()[(x().length-2)] - x()[0];
        double last_y = y()[(x().length-2)] - y()[0];
        double theda = Math.toDegrees(Math.atan((y()[y().length-1]-last_y)/(x()[y().length-1]-last_x)));
        return theda;
    }


}
