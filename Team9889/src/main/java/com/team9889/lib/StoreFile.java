package com.team9889.lib;

import com.qualcomm.robotcore.util.ReadWriteFile;
import com.team9889.Team9889Linear;

import org.firstinspires.ftc.robotcore.internal.system.AppUtil;

import java.io.File;

/**
 * Created by joshua9889 on 10/8/2017.
 */

public class StoreFile {

    public void writeFile(String fileName, String data, Team9889Linear team9889Linear) {
        try {
            File file = AppUtil.getInstance().getSettingsFile(fileName);
            ReadWriteFile.writeFile(file, data);
        } catch (Exception e) {
            team9889Linear.InternalopMode.telemetry.addData("Error","Writing to file");
            team9889Linear.InternalopMode.telemetry.update();
        }
    }

    public String readFile(String fileName, Team9889Linear team9889Linear) {
        try {
            File file = AppUtil.getInstance().getSettingsFile(fileName);
            return ReadWriteFile.readFile(file);
        } catch (Exception e){
            team9889Linear.InternalopMode.telemetry.addData("Error","Reading from file");
            team9889Linear.InternalopMode.telemetry.update();
            return "";
        }
    }
}
