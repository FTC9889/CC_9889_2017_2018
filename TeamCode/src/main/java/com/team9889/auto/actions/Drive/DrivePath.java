package com.team9889.auto.actions.Drive;

import com.team9889.auto.AutoModeBase;
import com.team9889.auto.actions.Action;
import com.team9889.auto.paths.PathContainer;

import java.util.ArrayList;

/**
 * Created by joshua9889 on 2/18/2018.
 */

public class DrivePath implements Action {
    private PathContainer path;
    private AutoModeBase autoModeBase;

    private ArrayList<Action> actions;

    public DrivePath(AutoModeBase autoModeBase, PathContainer path){
        this.path = path;
        this.autoModeBase = autoModeBase;
    }

    @Override
    public void start() {
        actions = new ArrayList<>(path.x().length);

        double origin_x = path.x()[0];
        double origin_y = path.y()[0];

        for(int i=1;i<path.x().length;i++){
            double distance_x = path.x()[i] - origin_x;
            double distance_y = path.y()[i] - origin_y;
        }
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void done() {

    }
}
