package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftMiddleHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new LStartSideLeftMiddleHatchPath();
        //Path secondPath = new LMiddleHatchStartLeftHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                // new FollowPathAction(secondPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
        )));
    }
}
