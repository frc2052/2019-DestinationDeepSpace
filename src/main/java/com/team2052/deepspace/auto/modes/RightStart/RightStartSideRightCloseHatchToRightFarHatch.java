package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightFarHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RCloseHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RFarHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;

import java.util.Arrays;

public class RightStartSideRightCloseHatchToRightFarHatch extends AutoMode {
    private Action myAction;
    public RightStartSideRightCloseHatchToRightFarHatch(int forwardOffset){
        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.RIGHT);
        setForwardStartOffset(forwardOffset);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightCloseHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new RCloseHatchStartRightHatchPickUpPath()),
                        new GroundIntakeAction(false))
                ),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new RHatchPickUpStartRightFarHatchPathCompoundPath().getPaths()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RFarHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
