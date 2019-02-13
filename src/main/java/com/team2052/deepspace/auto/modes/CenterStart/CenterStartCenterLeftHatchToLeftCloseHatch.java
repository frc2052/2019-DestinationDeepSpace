package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchCompoundPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterLeftHatchToLeftCloseHatch extends AutoMode {
    private Action myAction;
    public CenterStartCenterLeftHatchToLeftCloseHatch(){
        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.CENTER);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterLeftHatchPath(Path.Direction.BACKWARD)),
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
                new FollowPathListAction(new LHatchPickUpStartLeftCloseHatchCompoundPath().getPaths()),
                //Vision
                new LineUpAction(),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Turns robot around and drives back towards loading station
                new FollowPathListAction(new LCloseHatchStartLeftHatchPickUpPathCompoundPath().getPaths())
        ));
    }
    @Override
    protected void init() {
        //two hatch auto, the robot will drive backwards with hatch panel on ground pickup to start
        runAction(myAction);
    }
}
