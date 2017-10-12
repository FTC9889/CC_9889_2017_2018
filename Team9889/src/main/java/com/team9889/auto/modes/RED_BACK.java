package com.team9889.auto.modes;

import android.widget.TextView;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.*;

import org.w3c.dom.Text;

/**
 * Created by joshua9889 on 4/10/2017.
 */

@Autonomous(name = "Red Close")
@Disabled
public class RED_BACK extends AutoModeBase{

    @Override
    public void runOpMode() throws InterruptedException {
        waitForTeamStart(this, true);

    }
}
