package com.team9889.auto.actions;

import com.team9889.Team9889Linear;

/**
 * Created by joshua9889 on 11/10/2017.
 */

public class JewelHitColor implements Action {
    // The ball we leave on the thing
    private Team9889Linear.JewelColor jewelColor;

    public JewelHitColor(Team9889Linear.JewelColor jewelColor){
        this.jewelColor = jewelColor;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start(Team9889Linear opMode) {
        //TODO: Need to add activity for autonomous settings.
        if (opMode.jewel_Color != null){
            opMode.Robot.getJewel().deploy();
            opMode.sleep(1000);
            if (opMode.jewel_Color == this.jewelColor)
                opMode.Robot.getJewel().left();
            else if (opMode.jewel_Color != jewelColor)
                opMode.Robot.getJewel().right();
            opMode.sleep(350);
        }
        opMode.Robot.getJewel().retract();
    }

    @Override
    public void update(Team9889Linear linearOpMode) {

    }

    @Override
    public void done() {

    }
}
