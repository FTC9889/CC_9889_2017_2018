package com.team9889.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.team9889.Constants;
import com.team9889.Team9889LinearOpMode;

/**
 * Created by Jin on 11/3/2017.
 */

public class GlyphLypht extends Subsystem{

    DcMotor RGlyphLypht, LGlyphLypht = null;

    @Override
    public void outputToTelemetry(Team9889LinearOpMode opMode) {

    }

    @Override
    public boolean init(Team9889LinearOpMode team9889LinearOpMode, boolean auton) {
        try{
            this.RGlyphLypht = team9889LinearOpMode.hardwareMap.dcMotor.get(Constants.RGlyphLypht);
            this.LGlyphLypht = team9889LinearOpMode.hardwareMap.dcMotor.get(Constants.LGlyphLypht);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public void stop() {

    }

    @Override
    public void zeroSensors() {

    }
}
