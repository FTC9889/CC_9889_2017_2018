package org.simbotics.robot.util;

/**
 *
 * @author jason
 */
public class SimPosition {
    private double x, y;
    
    public SimPosition() {
        this.x = 0;
        this.y = 0;
    }
    
    /**
     * computes the new position of the robot based on the distance driven 
     * and angle from the gyro since the last update.
     * @param distance    distance driven over the last cycle
     * @param angle       average angle over the last cycle
     */
    public void update(double distance, double angle) {
        this.x += distance * Math.cos(Math.toRadians(angle));
        this.y += distance * Math.sin(Math.toRadians(angle));
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {    
        return this.y;
    }
    
    public void reset() {
        this.x = 0.0;
        this.y = 0.0;
    }
}
