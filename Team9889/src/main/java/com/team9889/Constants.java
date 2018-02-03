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

    // Robot Specs
    private final static double FinalGearReduction = 1. / 1.;
    private final static float WheelDiameter = 4;

    // NeveRest 40 Encoders
    private final static float PPR = 28;
    private final static double EncoderCounts40 = PPR * FinalGearReduction;

    // NeveRest 20 Encoders
    private final static double OPR = 134.4;
    private final static double EncoderCounts20 = OPR * FinalGearReduction;

    // Same output always
    public final static double CountsPerInch = EncoderCounts20 / (WheelDiameter*Math.PI);

    public static double ticks2Inches(int ticks){
        return ticks/Constants.CountsPerInch;
    }

    public static int inches2Ticks(int in){
        return (int)(in * CountsPerInch);
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
    public final static double DeployedJewelArm = 0.16;

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

    // Max speed of lift
    public final static double maxSpeed = 1.0;

    public final static int GLintake = 0;
    public final static int GLsecond = 400;
    public final static int GLtop = 900;
    public final static int GLback = 1430;

    //Lift Servos
    public final static String kLeftGlyphServoId = "id1";
    public final static String kRightGlyphServoId = "id2";

    //Finger Servos
    public final static String kLeftFingerId = "id3";
    public final static String kRightFingerId = "id4";

    /*---------------------
    |                     |
    |       Intake!       |
    |                     |
    ---------------------*/

    public final static String kLeftMotorIntakeId = "li";
    public final static String kRightMotorIntakeId = "ri";

    public final static String kLeftServoIntakeId = "ls";
    public final static String kRightServoIntakeId = "rs";

    /*---------------------
    |                     |
    |        Relic        |
    |                     |
    ---------------------*/

    public final static String kRelicMotor = "relicMotor";
    public final static String kLargeServo = "ws";
    public final static String kSmallServo = "grab";
}
