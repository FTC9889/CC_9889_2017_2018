package com.team9889.lib;

/**
 * Created by Joshua on 6/23/17.
 */

public class CruiseLib {
    private CruiseLib(){}

    public static double Average(double first, double second){
        return (first+second)/2;
    }

    public static int Average(int first, int second){
        return (first+second)/2;
    }

    public static boolean isBetween(double sample, double min, double max){
        return min < sample && sample < max;
    }

    public static double limitValue(double val) {
        return limitValue(val, 1.0);
    }

    public static double limitValue(double val, double max) {
        if(val > max) {
            return max;
        } else if(val < -max) {
            return -max;
        } else {
            return val;
        }
    }

    public static double limitValue(double val, double max, double min) {
        if(val > max) {
            return max;
        } else if(val < min) {
            return min;
        } else {
            return val;
        }
    }

    public static double squareMaintainSign(double val) {
        double output = val * val;

        //was originally negative
        if(val < 0) {
            output = -output;
        }

        return output;
    }

    public static double power3MaintainSign(double val){
        double output = val*val*val;
        return output;
    }

    public static double calcLeftTankDrive(double x, double y) {
        return limitValue(y + x);
    }

    public static double calcRightTankDrive(double x, double y) {
        return limitValue(y - x);
    }

    public static double max(double a, double b, double c) {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
        if(a > b && a > c) {
            return a;
        } else if(b > c) {
            return b;
        } else {
            return c;
        }
    }

    /**
     * @param deg Degree measure
     * @return Radian value
     */
    public static double degreesToRadians(double deg) {
        return (deg * Math.PI) / 180.0;
    }

    /**
     * @param rad Radian measure
     * @return Degree value
     */
    public static double radianToDegrees(double rad) {
        return (rad / Math.PI) * 180.0;
    }


    public static class RunningAverage{
        public RunningAverage(){}

        private int totalNumbers = 0;
        private double total = 0;
        public void update(double currentValue){
            totalNumbers++;
            total += currentValue;
        }

        public double get(){
            return total/(double)totalNumbers;
        }
    }

}
