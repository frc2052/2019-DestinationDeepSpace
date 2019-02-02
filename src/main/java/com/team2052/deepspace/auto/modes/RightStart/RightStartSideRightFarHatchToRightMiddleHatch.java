package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightMiddleHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RFarHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightFarHatchPath;

import java.util.Arrays;

public class RightStartSideRightFarHatchToRightMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightFarHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(),
                //TODO: change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(new RFarHatchStartRightHatchPickUpPath()),
                //Vision
                new VisionAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new RHatchPickUpStartRightMiddleHatchPathCompoundPath().getPaths()),
                //Vision
                new VisionAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RMiddleHatchStartRightHatchPickUpPathCompoundPath1().getPaths())
        )));
    }
}
