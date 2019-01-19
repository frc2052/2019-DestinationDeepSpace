package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterStart.CStartSideRightCloseHatchPath;
import com.team2052.deepspace.auto.paths.LeftMiddleHatchStart.LMiddleHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightCloseHatchStart.RCloseHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightCloseHatchStart.RCloseHatchStartRightHatchPickUpPath;

import java.util.Arrays;

public class CStartSideRightCloseHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new CStartSideRightCloseHatchPath();
        Path secondPath = new RCloseHatchStartRightHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchAction(HatchAction.Mode.OUT),
                new FollowPathAction(secondPath),
                new HatchAction(HatchAction.Mode.IN)
        )));
    }
}
