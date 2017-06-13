package org.simbotics.robot.util;

public class SimPoint {
	private double x;
	private double y;
	
	public SimPoint(double x, double y){
		this.x = x;
		this.y = y;
	}

    public SimPoint(){
        this.x = 0.0;
        this.y = 0.0;
    }
	
	public double getX(){
		return this.x;
	}
	
	public double getY(){
		return this.y;
	}
}
