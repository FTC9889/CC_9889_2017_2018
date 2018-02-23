package com.team9889.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Drive.DriveTimeAction;
import com.team9889.auto.actions.Drive.DriveToDistance;
import com.team9889.auto.actions.Drive.TurnLeftMotor;
import com.team9889.auto.actions.Drive.TurnRightMotor;
import com.team9889.auto.actions.Drive.TurnToAngle;
import com.team9889.auto.actions.Glyph.GlyphDeployOverTheBack;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevel;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphRelease;
import com.team9889.auto.actions.Glyph.GlyphRetractArm;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.auto.actions.Intake.IntakeDeployAndCollect;
import com.team9889.auto.actions.Intake.IntakeDeployWideIntake;
import com.team9889.auto.actions.Intake.IntakePull;
import com.team9889.auto.actions.Intake.IntakeSwivel;
import com.team9889.auto.actions.Jewel.JewelHitColor;
import com.team9889.auto.modes.SingleGlyph.BLUE_BACK;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by joshua9889 on 2/6/2018.
 */

@Autonomous(name = "Test Auto")
@Disabled
public class TestAuto extends AutoModeBase {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart(true, false);

        RelicRecoveryVuMark column = RelicRecoveryVuMark.LEFT;

        new BLUE_BACK(this, column);
    }
}