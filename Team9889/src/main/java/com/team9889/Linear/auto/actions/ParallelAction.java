package com.team9889.Linear.auto.actions;

import com.team9889.Linear.Team9889LinearOpMode;

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
    public void start(Team9889LinearOpMode opMode) {
        for (Action action : mActions) {
            action.start(opMode);
        }
    }

    @Override
    public void update(Team9889LinearOpMode  linearOpMode) {
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
