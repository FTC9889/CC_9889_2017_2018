package com.team9889.ftc2017;

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
    public static final String kFlywheelMotorId = "flywheel";

    //Settings for Drivetrain class
    public static final String kLeftDriveMasterId = "LDrive1";
    public static final String kLeftDriveSlaveId = "LDrive2";
    public static final String kRightDriveMasterId = "RDrive1";
    public static final String kRightDriveSlaveId = "RDrive2";

    public static final String kOpticalDistanceSensor1Id = "OD1";
    public static final String kOpticalDistanceSensor2Id = "OD2";

    public static final String kGyroId = "gyro";

    public static final String kLegoUltrasonicSensor1Id = "ultra";

    public static final String kCoreDeviceInterfaceModule1Id = "CDI";

    //DcMotor Encoders
    private static final float EncoderCounts=1120;
    private static final float WheelDiameter=4;
    public static final double CountsPerInch=EncoderCounts/(WheelDiameter*Math.PI);

    //Max and min speeds for drivetrain
    public final static double kDriveMaxSpeed = 1.0;
    public final static double kDriveMinSpeed = 0.2;

    //Value of white line
    public final double WhiteLineValue = 0.6;

    //Settings for Beacon Pressers
    public static final String kRightBeaconPresserId = "RBump";
    public static final String kLeftBeaconPresserId = "LBump";

    public static final String kColorSensorId = "colorsensor";

    public static final double kDeployedBeaconPresser = 0.4;
    public static final double kRetractedBeaconPresser = 0.4;

    //Settings Autonomous Mode
    public enum AutonSetting {
        FAR, CLOSE, DO_NOT_RUN, TEST
    }

    public enum Alliance_Color {
        RED, BLUE
    }

    public static Constants.Alliance_Color allianceColor_ = Alliance_Color.RED;
    public static Constants.AutonSetting autonSetting_ = AutonSetting.DO_NOT_RUN;

}
