package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterRightHatchStart.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterRightHatchToRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new CStartCenterRightHatchPath();
        Path secondPath = new CRightHatchStartRightHatchPickUpPath();
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
