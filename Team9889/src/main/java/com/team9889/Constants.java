package com.team9889;

/**
 * Class to store constants
 * Created by joshua9889 on 4/10/2017.
 */

public class Constants {
    //VuMark Licence Key
    public final static String kVuforiaLicenceKey = "AUEjQhP/////AAAAGV8qq1GGcE03gQHKWYPRZBNIZYZq8DoUn9lOpeWPY8PZKG1B5UtrsqkqPqh8Vcuv+HuUuobUNZE35fhYNPqxRIWqtoKbUmkZZCspmu0Aw685D5dxk87dT38/oYxzdKFs3EZaD8hvprmWj2Oww4+GtxS+fiImc23ZlkU20esE1MhwvX0xJ8tjoPS9pdTVSt1QtoYp3WxSxtZlKd0B0UMCxzj0KxN4JZRlTmF2W3dLU2G9SJ3hQO8jcC+Nuvbfk809C6LSWiijZ9L7IZNcQQiFKDa5yJP+ayX+Y1cpwcV19yqlPQTH7CQqcvnZDfwQZXmCpDhcQpW9h+bCXaerRH/uWNZMskyO0AXeFa1oCgB3EGPB";

    /*---------------------
    |                     |
    |     Drivetrain!     |
    |                     |
    ---------------------*/

    //Settings for MRDrive class
    public final static String kLeftDriveMasterId = "left";
    public final static String kRightDriveMasterId = "right";

    //DcMotor Encoders
    private final static float PPR = 28;
    private final static double FinalGearReduction = 40 / 1.5;
    private final static float WheelDiameter=4;
    private final static double EncoderCounts= PPR * FinalGearReduction;

    public final static double CountsPerInch=EncoderCounts/(WheelDiameter*Math.PI);

    //Max and min speeds for drivetrain
    public final static double kDriveMaxSpeed = 1.0;
    public final static double kDriveMinSpeed = 0.3;

    //Distance between wheels
    public final static double TrackWidth = 16;

    public static double ticks2Inches(int ticks){
        return ticks/Constants.CountsPerInch;
    }

    public static int inches2Ticks(int in){
        return in * (int)CountsPerInch;
    }

    /*---------------------
    |                     |
    |       Jewels!       |
    |                     |
    ---------------------*/

    //Settings for Jewels
    public final static String kJewelArmId = "jewel_arm";
    public final static String kJewelWristId = "jewel_wrist";

    public final static double RetractedJewelArm = 0.73;
    public final static double DeployedJewelArm = 0.20;

    public final static double CenterJewelWrist = 0.52;
    public final static double LeftJewelWrist = 0.0;
    public final static double RightJewelWrist = 1.0;

    /*---------------------
    |                     |
    |    GlyphtLypht!     |
    |                     |
    ---------------------*/

    //GlyphtLypht motor
    public final static String kLeftGlyphLift = "llift";
    public final static String kRightGlyphLift = "rlift";

    public final static int GLintake = 0;
    public final static int GLsecond = 400;
    public final static int GLtop = 900;

    //Lift Servos
    public final static String kLeftGlyphServoId = "id1";
    public final static String kRightGlyphServoId = "id2";

    //Finger Servos
    public final static String kLeftFingerId = "id3";
    public final static String kRightFingerId = "id4";

    public final static double maxSpeed = 0.4;

    /*---------------------
    |                     |
    |       Intake!       |
    |                     |
    ---------------------*/

    public final static String kLeftMotorIntakeId = "li";
    public final static String kRightMotorIntakeId = "ri";

    public final static String kLeftServoIntakeId = "ls";
    public final static String kRightServoIntakeId = "rs";

    // Balance Stone
    public final static String kBalanceThing = "bt";
    public final static double BalanceThingLowered = 0.0;
    public final static double BalanceThingRaised = 1.0;
}
