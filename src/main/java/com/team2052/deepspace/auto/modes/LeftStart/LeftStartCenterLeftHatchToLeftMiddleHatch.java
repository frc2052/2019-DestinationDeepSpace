package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftMiddleHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LMiddleHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartCenterLeftHatchToLeftMiddleHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartCenterLeftHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new CLeftHatchStartLeftHatchPickUpPath()),
                        new GroundIntakeAction(false))
                ),

                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new LHatchPickUpStartLeftMiddleHatchPathCompoundPath().getPaths()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new LMiddleHatchStartLeftHatchPickUpPathCompoundPath().getPaths())
        )));
    }
}
