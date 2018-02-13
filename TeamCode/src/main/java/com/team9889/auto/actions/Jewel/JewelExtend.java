package com.team9889.auto.actions.Jewel;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.team9889.auto.actions.Action;
import com.team9889.subsystems.Jewel;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 1/2/2018.
 */

public class JewelExtend implements Action {

    private Jewel jewel = Robot.getInstance().getJewel();
    private ElapsedTime t = new ElapsedTime();

    public JewelExtend(){}

    @Override
    public void start() {
        jewel.out();
        t.reset();
    }

    @Override
    public void update() {
        if(t.milliseconds()>500)
            jewel.deploy();
    }

    @Override
    public boolean isFinished() {
        return t.milliseconds()>1500;
    }

    @Override
    public void done() {

    }
}
