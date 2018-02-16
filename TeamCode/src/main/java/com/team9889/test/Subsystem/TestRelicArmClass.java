package com.team9889.test.Subsystem;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.team9889.Team9889Linear;
import com.team9889.subsystems.Relic;

/**
 * Created by joshua9889 on 2/16/2018.
 */

@TeleOp
@Disabled
public class TestRelicArmClass extends Team9889Linear {
    Relic mRelic;
    @Override
    public void runOpMode() throws InterruptedException {
        mRelic = new Relic();
        mRelic.init(this, true);

        waitForStart();

        while (opModeIsActive()){
            if(gamepad1.a){
                mRelic.goTo(Relic.RelicState.DEPLOYTOINTAKE);
            } else if(gamepad1.b){
                mRelic.goTo(Relic.RelicState.RETRACTED);
            } else if(gamepad1.x){
                mRelic.goTo(Relic.RelicState.THRIRDZONE);
            }
        }
    }
}
