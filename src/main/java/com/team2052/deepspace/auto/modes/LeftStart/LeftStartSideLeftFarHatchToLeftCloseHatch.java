package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchCompoundPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LFarHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftFarHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftFarHatchToLeftCloseHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftFarHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new VisionAction(),
                //TODO: change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(new LFarHatchStartLeftHatchPickUpPath()),
                //Vision
                new VisionAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new LHatchPickUpStartLeftCloseHatchCompoundPath().getPaths()),
                //Vision
                new VisionAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new LCloseHatchStartLeftHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
