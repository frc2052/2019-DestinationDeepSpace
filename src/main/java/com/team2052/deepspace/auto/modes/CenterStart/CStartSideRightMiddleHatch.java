package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterStart.CStartSideRightMiddleHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightFarHatchStart.RFarHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightMiddleHatchStart.RMiddleHatchStartRightHatchPickUpPath;

import java.util.Arrays;

public class CStartSideRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new CStartSideRightMiddleHatchPath();
        Path secondPath = new RMiddleHatchStartRightHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchAction(HatchAction.Mode.OUT),
                new FollowPathAction(secondPath),
                new HatchAction(HatchAction.Mode.IN)
        )));
    }
}
