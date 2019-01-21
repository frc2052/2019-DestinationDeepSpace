package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.LeftFarHatchStart.LFarHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideLeftFarHatchPath;

import java.util.Arrays;

public class RStartSideLeftFarHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new RStartSideLeftFarHatchPath();
        Path secondPath = new LFarHatchStartLeftHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchAction(HatchAction.Mode.OUT),
                new FollowPathAction(secondPath),
                new HatchAction(HatchAction.Mode.IN)
        )));
    }
}
