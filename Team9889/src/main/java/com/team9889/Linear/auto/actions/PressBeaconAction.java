package com.team9889.Linear.auto.actions;

import com.team9889.Constants;
import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.Linear.subsystems.*;

/**
 * Created by Joshua H on 4/11/2017.
 */

public class PressBeaconAction implements Action {
    Drive mDrive;
    Beacon mBeacon;

    private static boolean isFinished = false;

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void update(Team9889LinearOpMode  linearOpMode){
        if(!linearOpMode.opModeIsActive() && linearOpMode.isStopRequested()){
            done();
        }
    }


    @Override
    public void start(Team9889LinearOpMode opMode) {
        mDrive = opMode.mDrive;
        mBeacon = opMode.mBeacon;

        isFinished = false;

        if(Constants.Alliance == "RED"){
            if(mBeacon.getColor() == Beacon.BeaconColor.Red){
                mBeacon.WantedState(Beacon.Position.LEFT_DEPLOYED);
            }else {
                mBeacon.WantedState(Beacon.Position.RIGHT_DEPLOYED);
            }
        }else if(Constants.Alliance == "BLUE"){
            if(mBeacon.getColor() == Beacon.BeaconColor.Blue){
                mBeacon.WantedState(Beacon.Position.LEFT_DEPLOYED);
            }else {
                mBeacon.WantedState(Beacon.Position.RIGHT_DEPLOYED);
            }
        }

        new DriveStraightAction(3, Constants.kDriveMinSpeed);

        isFinished = true;
    }

    @Override
    public void done() {
        mDrive.setLeftRightPower(0,0);
        mBeacon.WantedState(Beacon.Position.BOTH_RETRACTED);
    }
}