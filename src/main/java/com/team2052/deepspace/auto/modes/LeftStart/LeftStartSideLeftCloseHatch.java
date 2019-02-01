package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftCloseHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //TODO: Make starting path start going backwards
                new FollowPathAction(new LStartSideLeftCloseHatchPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
