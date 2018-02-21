package com.team9889.auto.actions.Drive;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Action;
import com.team9889.auto.actions.Glyph.GlyphDeployToFirstLevelTwoGlyph;
import com.team9889.auto.actions.Glyph.GlyphDeployToIntake;
import com.team9889.auto.actions.Glyph.GlyphStorePreload;
import com.team9889.test.Autonomus.Control.paths.PathContainer;

import java.util.ArrayList;

/**
 * Created by joshua9889 on 2/18/2018.
 */

public class DrivePath implements Action {
    private PathContainer path;
    private AutoModeBase autoModeBase;

    public DrivePath(AutoModeBase autoModeBase, PathContainer path){
        this.path = path;
        this.autoModeBase = autoModeBase;
    }

    @Override
    public void start() {
        if(path.x().length!=path.y().length || path.x().length!=path.alerts().length)
            throw new IllegalStateException("Path does not have the same amount of commands");


        // Offsets
        double offset_x = path.x()[0];
        double offset_y = path.y()[0];
        double lastTheda = 0;

        for(int i=0;i<path.x().length && autoModeBase.opModeIsActive();i++) {
            double x = path.x()[i] - offset_x;
            double y = path.y()[i] - offset_y;
            if(i!=0){
                double last_x = path.x()[i-1] - offset_x;
                double last_y = path.y()[i-1] - offset_y;
                double theda = Math.toDegrees(Math.atan((y-last_y)/(x-last_x)));
                double distance = Math.sqrt(((y-last_y)*(y-last_y))+((x-last_x)*(x-last_x)));
                if(lastTheda==theda)
                    autoModeBase.runAction(new DriveToDistance((int)distance, theda));
                else {
                    autoModeBase.runAction(new TurnToAngle((int)theda));
                    autoModeBase.runAction(new DriveToDistance((int)distance, theda));
                }

                switch (path.alerts()[i]){
                    case None:
                        break;
                    case StorePreload:
                        autoModeBase.runAction(new GlyphStorePreload());
                        autoModeBase.runAction(new GlyphDeployToIntake());
                        break;
                    case DeployToIntake:
                        autoModeBase.runAction(new GlyphDeployToIntake());
                        break;
                    case DelpoyToFirstLevel:
                        autoModeBase.runAction(new GlyphDeployToFirstLevelTwoGlyph());
                        break;
                }


                lastTheda = theda;
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void done() {

    }
}
