package com.team9889.auto;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.Team9889Linear;
import com.team9889.auto.actions.Action;

/**
 * Created by joshua9889 on 8/5/2017.
 */

public abstract class AutoModeBase extends Team9889Linear {

    /**
     * @param action All actions are defined in action folder
     */
    public void runAction(Action action){
        this.InternalopMode.telemetry.addData("Starting Action", "");
        this.InternalopMode.telemetry.update();

        action.start(this.InternalopMode);

        this.InternalopMode.telemetry.addData("Running Action", "");
        this.InternalopMode.telemetry.update();
        while(!action.isFinished() && this.InternalopMode.opModeIsActive()){

            action.update(this.InternalopMode);
            Thread.yield();
        }
        action.done();

        this.InternalopMode.telemetry.addData("Finished Action", "");
        this.InternalopMode.telemetry.update();
    }

}