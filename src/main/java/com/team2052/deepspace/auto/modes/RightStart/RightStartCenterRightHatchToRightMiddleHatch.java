package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightMiddleHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RMiddleHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartCenterRightHatchPath;

import java.util.Arrays;

public class RightStartCenterRightHatchToRightMiddleHatch extends AutoMode {
    private Action myAction;
    public RightStartCenterRightHatchToRightMiddleHatch(int forwardOffset){
        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.RIGHT);
        setForwardStartOffset(forwardOffset);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartCenterRightHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new CRightHatchStartRightHatchPickUpPath()),
                        new GroundIntakeAction(false))
                ),
                //Vision
                new LineUpAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                new FollowPathListAction(new RHatchPickUpStartRightMiddleHatchPathCompoundPath().getPaths()),
                //Vision
                new LineUpAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RMiddleHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
