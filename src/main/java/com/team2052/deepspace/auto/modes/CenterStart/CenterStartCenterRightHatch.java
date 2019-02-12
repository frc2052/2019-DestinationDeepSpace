package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;

import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterRightHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterRightHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when false, ground outtake action
                new GroundIntakeAction(true),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new CRightHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
