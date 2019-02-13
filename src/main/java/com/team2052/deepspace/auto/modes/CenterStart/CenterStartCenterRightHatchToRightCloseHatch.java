package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightCloseHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RCloseHatchStartRightHatchPickUpPathCompoundPath;

import java.util.Arrays;

public class CenterStartCenterRightHatchToRightCloseHatch extends AutoMode {
    private Action myAction;
    public CenterStartCenterRightHatchToRightCloseHatch(){
        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.CENTER);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterRightHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when false, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new CRightHatchStartRightHatchPickUpPath()),
                        new GroundIntakeAction(false))
                ),
                //Vision
                new LineUpAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Compound path to make robot turn around
                //new FollowPathListAction(new RHatchPickUpStartRightCloseHatchPathCompoundPath().getPaths()),
                //Vision
                new LineUpAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new RCloseHatchStartRightHatchPickUpPathCompoundPath().getPaths())
        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
