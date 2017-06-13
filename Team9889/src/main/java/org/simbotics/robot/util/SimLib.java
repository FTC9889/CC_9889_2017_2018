/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.simbotics.robot.util;

/**
 *
 * @author Mike
 */
public class SimLib {
    
    public static double limitValue(double val) {
        return SimLib.limitValue(val, 1.0);
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
        return SimLib.limitValue(y + x);
    }
    
    public static double calcRightTankDrive(double x, double y) {
        return SimLib.limitValue(y - x);
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
    
    
    public static boolean isWithinRange(double target, double val, double eps){
    	if(Math.abs(val - target) <= eps){
    		return true;
    	}else{
    		return false;
    	}
    }

    public static boolean isWithinRange(double target, double val){
        return isWithinRange(target, val, 10);
    }
    
}
