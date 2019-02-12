package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightMiddleHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RCloseHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RMiddleHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;

import java.util.Arrays;

public class RightStartSideRightCloseHatchToRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightCloseHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new FollowPathAction(new RCloseHatchStartRightHatchPickUpPath()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new RHatchPickUpStartRightMiddleHatchPathCompoundPath().getPaths()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RMiddleHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
