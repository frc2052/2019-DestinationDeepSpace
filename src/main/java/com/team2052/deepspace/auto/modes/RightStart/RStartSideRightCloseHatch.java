package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightCloseHatchStart.RCloseHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;

import java.util.Arrays;

public class RStartSideRightCloseHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new RStartSideRightCloseHatchPath();
        Path secondPath = new RCloseHatchStartRightHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchAction(HatchAction.Mode.OUT),
                new FollowPathAction(secondPath),
                new HatchAction(HatchAction.Mode.IN)
        )));
    }
}
