package com.team9889;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Constants {

    //Settings for Intake class
    protected final static String kIntakeMotorId = "IntakeMotor";
    protected final static String kIntakeServo = "Intake";
    protected final static double kIntakeMotorSpeedIntake = 1.0;
    protected final static double kIntakeServoSpeedIntake = 1.0;
    protected final static double kIntakeMotorSpeedShoot = 1.0;
    protected final static double kIntakeServoSpeedShoot = -1.0;
    protected final static double kIntakeMotorSpeedOuttake = 1.0;
    protected final static double kIntakeServoSpeedOuttake = 0.5;
    protected final static double kIntakeMotorSpeedWait = 0.0;
    protected final static double kIntakeServoSpeedWait = 1.0;

    //Settings for Flywheel class
    protected final static String kFlywheelMotorId = "flywheel";

    //Settings for Drivetrain class
    protected final static String kLeftDriveMasterId = "LDrive1";
    protected final static String kLeftDriveSlaveId = "LDrive2";
    protected final static String kRightDriveMasterId = "RDrive1";
    protected final static String kRightDriveSlaveId = "RDrive2";

    protected final static String kOpticalDistanceSensor1Id = "OD1";
    protected final static String kOpticalDistanceSensor2Id = "OD2";

    protected final static String kGyroId = "gyro";

    protected final static String kLegoUltrasonicSensor1Id = "ultra";

    protected final static String kCoreDeviceInterfaceModule1Id = "CDI";

    //DcMotor Encoders
    private final static float EncoderCounts=1120;
    private final static float WheelDiameter=4;
    protected final static double CountsPerInch=EncoderCounts/(WheelDiameter*Math.PI);

    //Max and min speeds for drivetrain
    protected final static double kDriveMaxSpeed = 1.0;
    protected final static double kDriveMinSpeed = 0.3;

    //Value of white line
    protected final static double WhiteLineValue = 0.6;

    //Settings for Beacon Pressers
    protected final static String kRightBeaconPresserId = "RBump";
    protected final static String kLeftBeaconPresserId = "LBump";

    protected final static String kColorSensorId = "colorsensor";

    protected final static double kDeployedBeaconPresser = 1.0;
    protected final static double kRetractedBeaconPresser = 0.4;

    //Settings for failure tracking
    public static String OpMode;

    public static String Alliance;

    protected static double ticksToInches(int ticks){
        return ticks/Constants.CountsPerInch;
    }

}
