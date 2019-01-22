package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterLeftHatchStart.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartCenterLeftHatchPath;

import java.util.Arrays;

public class RightStartCenterLeftHatchToRightFarHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new RStartCenterLeftHatchPath();
        Path secondPath = new CLeftHatchStartLeftHatchPickUpPath();
        //Path thirdPath = new  RHatchPickUpStartRightFarHatchPath();
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
