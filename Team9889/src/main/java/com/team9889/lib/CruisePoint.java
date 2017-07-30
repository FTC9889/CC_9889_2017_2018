package com.team9889.lib;

/**
 * Created by Joshua on 7/29/2017.
 */

public class CruisePoint {
    private double x;
    private double y;

    public double getX(){
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setXY(double x, double y){
        this.setX(x);
        this.setY(y);
    }
}
