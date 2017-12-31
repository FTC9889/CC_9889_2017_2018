package com.team9889.lib.control;

/**
 * Uses ControlThread to control the motor outputs
 * Only designed to go straight.
 * Created by joshua9889 on 12/28/2017.
 */

public class DriveStraightControl implements ControlThread {

    public DriveStraightControl(double tolerance){
        this.tolerance = Math.abs(tolerance);
    }

    private double wantedLeftPosition, wantedRightPosition, wantedAngle;
    private double currentLeftPosition, currentRightPosition, currentAngle;

    private double cruiseVel = 2.2*1000; // Random Unit (maybe fpmilli)

    private long timeFromZeroToCruiseVel = 200; // Milli
    private double MaxAccPerMilli = cruiseVel/timeFromZeroToCruiseVel; // Random Unit (maybe fpmilli)

    private long timeFromCruiseVelToZero = 300;
    private double MaxDecPerMilli = cruiseVel/timeFromCruiseVelToZero;

    private long startTime;
    private long timeToRun;

    private boolean isAtLeft = false;
    private boolean isAtRight = false;
    private boolean isAtAngle = false;

    public int timesRun = 0;

    // Tolerance for Positions
    private double tolerance = 0;

    /**
     * @param left Setpoint for left side of Drivetrain. Is relative. Is in Feet
     * @param right Setpoint for right side of Drivetrain. Is relative. Is in Feet
     * @param angle Wanted Angle of Drivetrain. Is Absolute. Is in Degrees
     * @param millisecondsToExecute How long it should run for
     */
    public void newSetpoint(double left, double right, double angle, long millisecondsToExecute){
        this.wantedLeftPosition += left;
        this.wantedRightPosition += right;
        this.wantedAngle = angle;

        this.timeToRun = millisecondsToExecute;

        if(timeToRun < timeFromZeroToCruiseVel+(left*cruiseVel)+timeFromCruiseVelToZero)
            System.out.println("Not enough time to execute");
        if(timeToRun < timeFromZeroToCruiseVel+timeFromCruiseVelToZero)
            System.out.println("Not enough time to execute");

        this.isAtLeft = false;
        this.isAtRight = false;
        this.isAtAngle = false;
    }

    /**
     * @param angle Wanted Angle of Drivetrain. Is Absolute
     */
    public void newSetpoint(double angle, long millisecondsToExecute){
        newSetpoint(0, 0, angle, millisecondsToExecute);
    }

    /**
     * @param left Setpoint for left side of Drivetrain. Is relative.
     * @param right Setpoint for right side of Drivetrain. Is relative.
     */
    public void newSetpoint(double left, double right, long millisecondsToExecute){
        newSetpoint(left, right, wantedAngle, millisecondsToExecute);
    }

    /**
     * Check if the wanted == current
     * @return Is at the positions.
     */
    public boolean isAtPoint(){
        return isAtLeft; //&& isAtRight && isAtAngle;
    }

    boolean first = true;

    @Override
    public void update() {
        if(first){
            startTime = System.currentTimeMillis();
            first = false;
        }

        controlLeftBasic();


        timesRun++;
    }

    @Override
    public boolean isAtGoal() {
        return isAtPoint();
    }

    public void controlLeftBasic(){
        if(currentLeftPosition+tolerance < wantedLeftPosition){
            currentLeftPosition += 0.001;
        } else if(currentLeftPosition-tolerance > wantedLeftPosition){
            currentLeftPosition -= 0.001;
        } else {
            isAtLeft = true;
        }
    }

    public void controlRightBasic(){
        if(currentRightPosition+tolerance < wantedRightPosition){
            currentRightPosition += 0.001;
        } else if(currentRightPosition-tolerance > wantedRightPosition){
            currentRightPosition -= 0.001;
        } else {
            isAtRight = true;
        }
    }

    public void controlAngleBasic(){
        if(currentAngle+tolerance < wantedAngle){
            currentAngle += 0.001;
        } else if(currentAngle-tolerance > wantedAngle){
            currentAngle -= 0.001;
        } else {
            isAtAngle = true;
        }
    }
}
