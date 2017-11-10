package com.team9889.lib;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.util.ReadWriteFile;
import com.team9889.Team9889LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

/**
 * Created by joshua9889 on 10/6/2017.
 */

public class RevIMU {

    private BNO055IMU imu = null;
    private Orientation angles = null;
    private Acceleration gravity = null;
    private String filename = "BNO055IMUCalibration.json";
    private String id = "";

    public RevIMU(String filename, String id){
        this.filename = filename;
        this.id = id;
    }

    public double getAbsAngle() {
        return angles.angleUnit.DEGREES.fromDegrees(0.0);
    }

    public void calibrate(){
        BNO055IMU.CalibrationData calibrationData = imu.readCalibrationData();
        File file = AppUtil.getInstance().getSettingsFile(filename);
        ReadWriteFile.writeFile(file, calibrationData.serialize());
    }

    public void setup(Team9889LinearOpMode team9889LinearOpMode) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = filename; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU_" + id;
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = team9889LinearOpMode.hardwareMap.get(BNO055IMU.class, id);
        imu.initialize(parameters);
    }

}
