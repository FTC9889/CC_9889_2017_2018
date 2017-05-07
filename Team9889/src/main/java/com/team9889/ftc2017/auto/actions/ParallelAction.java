package com.team9889.ftc2017.auto.actions;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;

/**
 * Created by joshua on 5/6/17.
 */

public class ParallelAction implements Action {
    ArrayList<Action> mActions;

    public ParallelAction(ArrayList<Action> actions){
        mActions = new ArrayList<>(actions.size());
        for(Action action : actions){
            mActions.add(action);
        }
    }

    @Override
    public boolean isFinished() {
        boolean all_finished = true;
        for (Action action : mActions) {
            if (!action.isFinished()) {
                all_finished = false;
            }
        }
        return all_finished;
    }

    @Override
    public void start(HardwareMap hardwareMap) {
        for (Action action : mActions) {
            action.start(hardwareMap);
        }
    }

    @Override
    public void update(LinearOpMode linearOpMode) {
        for (Action action : mActions) {
            action.update(linearOpMode);
        }
    }

    @Override
    public void done() {
        for (Action action : mActions) {
            action.done();
        }
    }
}
