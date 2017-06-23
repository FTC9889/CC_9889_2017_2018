package com.team9889;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Joshua H on 4/10/2017.
 */

public class Constants {

    //Settings for Intake class
    public final static String kIntakeMotorId = "IntakeMotor";
    public final static String kIntakeServo = "Intake";
    public final static double kIntakeMotorSpeedIntake = 1.0;
    public final static double kIntakeServoSpeedIntake = 1.0;
    public final static double kIntakeMotorSpeedShoot = 1.0;
    public final static double kIntakeServoSpeedShoot = -1.0;
    public final static double kIntakeMotorSpeedOuttake = 1.0;
    public final static double kIntakeServoSpeedOuttake = 0.5;
    public final static double kIntakeMotorSpeedWait = 0.0;
    public final static double kIntakeServoSpeedWait = 1.0;

    //Settings for Flywheel class
    public final static String kFlywheelMotorId = "flywheel";

    //Settings for Drivetrain class
    public final static String kLeftDriveMasterId = "LDrive1";
    public final static String kLeftDriveSlaveId = "LDrive2";
    public final static String kRightDriveMasterId = "RDrive1";
    public final static String kRightDriveSlaveId = "RDrive2";

    public final static String kOpticalDistanceSensor1Id = "OD1";
    public final static String kOpticalDistanceSensor2Id = "OD2";

    public final static String kGyroId = "gyro";

    public final static String kLegoUltrasonicSensor1Id = "ultra";

    public final static String kCoreDeviceInterfaceModule1Id = "CDI";

    //DcMotor Encoders
    private final static float EncoderCounts=1120;
    private final static float WheelDiameter=4;
    public final static double CountsPerInch=EncoderCounts/(WheelDiameter*Math.PI);

    //Max and min speeds for drivetrain
    public final static double kDriveMaxSpeed = 1.0;
    public final static double kDriveMinSpeed = 0.3;

    //Value of white line
    public final static double WhiteLineValue = 0.6;

    //Settings for Beacon Pressers
    public final static String kRightBeaconPresserId = "RBump";
    public final static String kLeftBeaconPresserId = "LBump";

    public final static String kColorSensorId = "colorsensor";

    public final static double kDeployedBeaconPresser = 1.0;
    public final static double kRetractedBeaconPresser = 0.4;

    //Settings for failure tracking
    public static String OpMode;

    public static String Alliance;

    public static double ticksToInches(int ticks){
        return ticks/Constants.CountsPerInch;
    }

}
