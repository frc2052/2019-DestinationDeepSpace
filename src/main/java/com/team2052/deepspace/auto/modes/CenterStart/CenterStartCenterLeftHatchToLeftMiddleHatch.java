package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterLeftHatchStart.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.leftPickUpStart.LeftPickupStartCenterRightPath;

import java.util.Arrays;

public class CenterStartCenterLeftHatchToLeftMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new CStartCenterLeftHatchPath();
        Path secondPath = new CLeftHatchStartLeftHatchPickUpPath();
        //Path thirdPath = new LHatchPickUpStartLeftMiddleHatchPath();
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
