package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RFarHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightFarHatchPath;

import java.util.Arrays;

public class RightStartSideRightFarHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightFarHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when false, ground outtake action
                new GroundIntakeAction(false),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RFarHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
