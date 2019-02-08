package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftFarHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LFarHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LMiddleHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftMiddleHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftMiddleHatchToLeftFarHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftMiddleHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when false, ground outtake action
                new GroundIntakeAction(false),
                new FollowPathAction(new LMiddleHatchStartLeftHatchPickUpPath()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new LHatchPickUpStartLeftFarHatchPathCompoundPath().getPaths()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new LFarHatchStartLeftHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
