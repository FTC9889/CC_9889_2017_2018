package com.team9889.auto;

import com.team9889.Team9889LinearOpMode;
import com.team9889.auto.actions.Action;

/**
 * Created by joshua9889 on 8/5/2017.
 */

public abstract class AutoModeBase extends Team9889LinearOpMode {

    /**
     * @param action All are defined in action folder
     */
    public void runAction(Action action){
        action.start(this.InternalopMode);
        while(!action.isFinished() && this.InternalopMode.opModeIsActive()){
            action.update(this.InternalopMode);
        }
        action.done();

        this.InternalopMode.telemetry.addData("Finished Action", "");
        this.InternalopMode.telemetry.update();
    }

}