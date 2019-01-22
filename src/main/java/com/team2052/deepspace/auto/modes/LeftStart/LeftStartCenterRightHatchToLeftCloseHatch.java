package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterRightHatchStart.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartCenterRightHatchToLeftCloseHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new LStartCenterRightHatchPath();
        Path secondPath = new CRightHatchStartRightHatchPickUpPath();
        //Path thirdPath = new LHatchPickUpStartLeftCloseHatchPath();
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
