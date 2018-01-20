package com.team9889.auto;

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
        telemetry.addData("Starting Action", "");
        telemetry.update();

        if(opModeIsActive())
            action.start();

        while(!action.isFinished() && opModeIsActive()){
            telemetry.addData("Running Action", "");
            updateTelemetry();
            action.update();
            Thread.yield();
        }
        action.done();

        telemetry.addData("Finished Action", "");
        telemetry.update();
    }

    public void ThreadAction(final Action action){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                runAction(action);
            }
        };

        if(opModeIsActive() && !isStopRequested())
            new Thread(runnable).start();
    }

}