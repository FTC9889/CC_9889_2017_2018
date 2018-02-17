package com.team9889.test.Autonomus.Control;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Drive;

/**
 * Created by joshua9889 on 2/17/2018.
 *
 * This was used to test the latency of the rev imu.
 * We found that when using two hubs the latency was ~50ms.
 * When using one hub the latency was ~20ms.
 */
@TeleOp
@Disabled
public class TestRevSpeed extends Team9889Linear {
    double lastVal;
    ElapsedTime t = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        Robot.getDrive().init(this, true);
        waitForStart();
        Robot.getDrive().DriveControlState(Drive.DriveControlStates.POWER);
        Robot.getDrive().DriveZeroPowerState(Drive.DriveZeroPowerStates.FLOAT);
        Robot.getDrive().setLeftRightPower(-1, 1);
        lastVal = Robot.getDrive().getGyroAngleDegrees();
        while (opModeIsActive()){
            if(Robot.getDrive().getGyroAngleDegrees()!=lastVal){
                RobotLog.a(String.valueOf(t.nanoseconds()) + " | " + String.valueOf(t.milliseconds()));
                t.reset();
            }
        }
        Robot.getDrive().setLeftRightPower(0,0);
    }
}
