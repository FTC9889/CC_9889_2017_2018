package com.team9889.auto.actions;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.lib.control.SynchronousPIDF;
import com.team9889.subsystems.Drive;

/**
 * Trying to get speed control working
 * Created by joshua9889 on 12/29/2017.
 */

public class DrivePID implements Action {
    //Our Drivetrain
    private Drive mDrive;

    // Wanted Speed
    private double wantedSpeed;

    private ElapsedTime t = new ElapsedTime();
    private double LastDistance;

    //TODO: Tune this pidf
    private SynchronousPIDF speedPid = new SynchronousPIDF(160, 32, 112, 40);

    public DrivePID(double speed){
        this.wantedSpeed = speed;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void start(Team9889Linear opMode) {
        mDrive = opMode.Robot.getDrive();

        if(wantedSpeed<0)
            speedPid.setOutputRange(-1, 0);
        else if(wantedSpeed>0)
            speedPid.setOutputRange(0, 1);

        LastDistance = mDrive.getLeftTicks();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {
        double TicksPerMilli = mDrive.getLeftTicks();
        LastDistance = mDrive.getLeftTicks();
    }

    @Override
    public void done() {

    }
}
