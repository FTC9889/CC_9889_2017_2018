package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.team9889.ftc2017.Constants;
import com.team9889.ftc2017.subsystems.Beacon;
import com.team9889.ftc2017.subsystems.Drive;

/**
 * Created by Joshua H on 4/11/2017.
 */

public class PressBeaconAction implements Action {
    Drive mDrive = Drive.getInstance();
    Beacon mBeacon = Beacon.getInstance();

    private static boolean isFinished = false;

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public void update(LinearOpMode linearOpMode){
        if(!linearOpMode.opModeIsActive() && linearOpMode.isStopRequested()){
            done();
        }
    }


    @Override
    public void start(HardwareMap hardwareMap) {
        mDrive.init(hardwareMap, false);
        mBeacon.init(hardwareMap, false);

        isFinished = false;

        if(Constants.allianceColor_ == Constants.Alliance_Color.RED){
            if(mBeacon.getColor() == Beacon.BeaconColor.Red){
                mBeacon.WantedState(Beacon.Position.LEFT_DEPLOYED);
            }else {
                mBeacon.WantedState(Beacon.Position.RIGHT_DEPLOYED);
            }
        }else if(Constants.allianceColor_ == Constants.Alliance_Color.BLUE){
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