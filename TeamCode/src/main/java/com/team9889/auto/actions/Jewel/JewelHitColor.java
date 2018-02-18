package com.team9889.auto.actions.Jewel;

import com.qualcomm.robotcore.util.RobotLog;
import com.team9889.Team9889Linear;
import com.team9889.auto.actions.Action;
import com.team9889.subsystems.Jewel;
import com.team9889.subsystems.Robot;

/**
 * Created by joshua9889 on 11/10/2017.
 */

public class JewelHitColor implements Action {

    private Jewel jewel = Robot.getInstance().getJewel();

    // The ball we leave on the thing
    private Team9889Linear.JewelColor jewelColor;
    private Team9889Linear opMode;

    public JewelHitColor(Team9889Linear.JewelColor jewelColor, Team9889Linear team9889Linear){
        this.jewelColor = jewelColor;
        this.opMode = team9889Linear;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void start() {
        //TODO: Need to add activity for autonomous settings.
        if (opMode.jewel_Color != null){
            jewel.deploy();
            sleep(700);
            if (opMode.jewel_Color == this.jewelColor)
                jewel.leftHit();
            else if (opMode.jewel_Color != jewelColor)
                jewel.rightHit();
            sleep(350);
        }

        // Wait for the arm to be up, then setPos of arm,
        // but do not wait to finish it, just keep running
        // the autonomous.
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep(1000);
                jewel.stop();

                if (opMode.opModeIsActive() && !opMode.isStopRequested())
                    jewel.stop();

            }
        }).start();

        RobotLog.a("Finished Hitting Jewel");
    }

    @Override
    public void update() {}

    @Override
    public void done() {}

    private void sleep(int millisecond){
        try {
            Thread.sleep(millisecond);
        } catch (InterruptedException e) {
        }
    }
}
