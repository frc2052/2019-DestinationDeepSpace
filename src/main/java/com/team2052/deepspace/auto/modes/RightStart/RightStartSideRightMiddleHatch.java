package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RMiddleHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RMiddleHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightMiddleHatchPath;

import java.util.Arrays;

public class RightStartSideRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightMiddleHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(),
                //TODO: change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RMiddleHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
