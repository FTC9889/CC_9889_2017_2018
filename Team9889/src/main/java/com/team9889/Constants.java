package com.team9889;

/**
 * Class to store constants
 * Created by joshua9889 on 4/10/2017.
 */

public class Constants {
    //Vuforia Licence Key
    public final static String kVuforiaLicenceKey = "AUEjQhP/////AAAAGV8qq1GGcE03gQHKWYPRZBNIZYZq8DoUn9lOpeWPY8PZKG1B5UtrsqkqPqh8Vcuv+HuUuobUNZE35fhYNPqxRIWqtoKbUmkZZCspmu0Aw685D5dxk87dT38/oYxzdKFs3EZaD8hvprmWj2Oww4+GtxS+fiImc23ZlkU20esE1MhwvX0xJ8tjoPS9pdTVSt1QtoYp3WxSxtZlKd0B0UMCxzj0KxN4JZRlTmF2W3dLU2G9SJ3hQO8jcC+Nuvbfk809C6LSWiijZ9L7IZNcQQiFKDa5yJP+ayX+Y1cpwcV19yqlPQTH7CQqcvnZDfwQZXmCpDhcQpW9h+bCXaerRH/uWNZMskyO0AXeFa1oCgB3EGPB";

    //Settings for Drive class
    public final static String kLeftDriveMasterId = "LDrive1";
    public final static String kLeftDriveSlaveId = "LDrive2";
    public final static String kRightDriveMasterId = "RDrive1";
    public final static String kRightDriveSlaveId = "RDrive2";

    public final static String kGyroId = "gyro";
    public final static String kCoreDeviceInterfaceModule1Id = "CDI";

    //DcMotor Encoders
    private final static float EncoderCounts=1120;
    private final static float WheelDiameter=4;
    public final static double CountsPerInch=EncoderCounts/(WheelDiameter*Math.PI);

    //Max and min speeds for drivetrain
    public final static double kDriveMaxSpeed = 1.0;
    public final static double kDriveMinSpeed = 0.3;

    //Distance between wheels
    public final static double TrackWidth = 16;

    public static double ticksToInches(int ticks){
        return ticks/Constants.CountsPerInch;
    }

}
