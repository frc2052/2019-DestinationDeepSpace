package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;

import java.util.Arrays;

public class CenterStartCenterLeftHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //TODO: Make starting path start going backwards
                new FollowPathAction(new CStartCenterLeftHatchPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
