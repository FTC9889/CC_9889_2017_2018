package com.team9889.Linear.lib.util;

import java.util.List;

/**
 * Created by Joshua on 8/6/2017.
 */

public class Waypoint {
    private List<Double> mXpoints_;
    private List<Double> mYpoints_;
    private List<Double> mAnglepoints_;
    private int newPointInt = 0;

    public void newPoint(double X, double Y, double Theda){
        mXpoints_.add(newPointInt, X);
        mYpoints_.add(newPointInt, Y);
        mAnglepoints_.add(newPointInt, Theda);
        newPointInt++;
    }

}
