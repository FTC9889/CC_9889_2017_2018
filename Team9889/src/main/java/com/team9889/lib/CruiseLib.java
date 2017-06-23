package com.team9889.lib;

/**
 * Created by joshua on 6/23/17.
 */

public class CruiseLib {
    static public double Average(double first, double second){
        return (first+second)/2;
    }

    static public int Average(int first, int second){
        return (first+second)/2;
    }

    static public boolean isBetween(double sample, double min, double max){
        return min < sample && sample < max;
    }
}
