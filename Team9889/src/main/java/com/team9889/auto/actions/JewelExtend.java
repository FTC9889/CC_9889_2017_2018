package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 1/2/2018.
 */

public class JewelExtend implements Action {

    public JewelExtend(){}

    @Override
    public void start(Team9889Linear opMode) {
        opMode.Robot.getJewel().deploy();
        opMode.sleep(500);
    }

    @Override
    public void update(Team9889Linear opMode) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {

    }
}
