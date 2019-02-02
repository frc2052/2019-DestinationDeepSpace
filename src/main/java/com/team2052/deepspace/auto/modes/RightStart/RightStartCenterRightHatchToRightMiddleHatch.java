package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightMiddleHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RMiddleHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartCenterRightHatchPath;

import java.util.Arrays;

public class RightStartCenterRightHatchToRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartCenterRightHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(),
                // when false, ground outtake action
                new GroundIntakeAction(false),
                new FollowPathAction(new CRightHatchStartRightHatchPickUpPath()),
                //Vision
                new VisionAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new RHatchPickUpStartRightMiddleHatchPathCompoundPath().getPaths()),
                //Vision
                new VisionAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RMiddleHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
