package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartCenterLeftCenterHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartCenterLeftHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when false, ground outtake action
                new GroundIntakeAction(false),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
