package com.team9889.Linear.auto;

import com.team9889.Linear.Team9889LinearOpMode;
import com.team9889.Linear.auto.actions.Action;

/**
 * Created by lego on 8/5/2017.
 */

public abstract class AutoModeBase extends Team9889LinearOpMode {

    /**
     * @param action All are defined in action folder
     */
    protected void runAction(Action action){
        //If there is an error, Stop the OpMode
        boolean error = false;
        try {
            action.start(this.InternalopMode);
            this.updateTelemetry();
        } catch (Exception e){
            this.InternalopMode.telemetry.addData("Error in Starting Action", action);
            this.InternalopMode.telemetry.update();
        }

        while (!action.isFinished() && this.InternalopMode.opModeIsActive() && !error){
            try {
                action.update(this.InternalopMode);
                this.updateTelemetry();
            } catch (Exception e){
                this.InternalopMode.telemetry.addData("Error in Updating Action", action);
                this.InternalopMode.telemetry.update();
            }
        }

        if (this.InternalopMode.opModeIsActive() && !error){
            try {
                action.done();
                this.updateTelemetry();
            } catch (Exception e){
                this.InternalopMode.telemetry.addData("Error in Finishing Action", action);
                this.InternalopMode.telemetry.update();
            }
        }

        this.InternalopMode.telemetry.addData("Finished Action", "");
        this.InternalopMode.telemetry.update();

        if(!error){
            this.mSuperstructure.stop();
            this.InternalopMode.sleep(5000);
        }
    }

}
