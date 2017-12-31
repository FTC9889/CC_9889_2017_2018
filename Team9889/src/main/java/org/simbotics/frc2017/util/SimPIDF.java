package org.simbotics.frc2017.util;

public class SimPIDF extends SimPID{
	
	
	private double feedForward;
	
	public SimPIDF(double p, double i, double d,double f,double eps){
		super(p, i, d,eps);
		this.feedForward = f;
	}
	
	@Override
	public double calcPID(double current){
		double feedForwardOutput = (super.getDesiredVal()*this.feedForward);
		return super.calcPID(current) + feedForwardOutput;
	}
	
	public void setConstants(double p, double i, double d, double f){
		super.setConstants(p, i, d);
		this.feedForward = f;
	}
	
	@Override
	public boolean isDone(){
		double currError = Math.abs(this.previousError);
        
        //close enough to target
        if(currError <= this.finishedRange) {
        	return true;
        }else{
        	return false;
        }
	}
	
	public void setFeedForward(double f){
		this.feedForward = f;
	}

}
