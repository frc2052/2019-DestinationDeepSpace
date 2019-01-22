package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterRightHatchStart.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartCenterRightHatchPath;

import java.util.Arrays;

public class RightStartCenterRightHatchToRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new RStartCenterRightHatchPath();
        Path secondPath = new CRightHatchStartRightHatchPickUpPath();
        //Path thirdPath = new  RHatchPickUpStartRightMiddleHatchPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchAction(HatchAction.Mode.OUT),
                new FollowPathAction(secondPath),
                new HatchAction(HatchAction.Mode.IN),
                new FollowPathAction(thirdPath),
                new HatchAction(HatchAction.Mode.OUT)
        )));
    }
}
