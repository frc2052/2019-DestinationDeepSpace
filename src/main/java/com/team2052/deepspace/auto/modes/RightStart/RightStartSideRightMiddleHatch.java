package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightMiddleHatchPath;

import java.util.Arrays;

public class RightStartSideRightMiddleHatch extends AutoMode {
    private Action myAction;
    public RightStartSideRightMiddleHatch(){
        super();
        setStartDirection(StartDirection.FORWARD);
        setLateralStartPosition(LateralStartPosition.RIGHT);
        //setForwardStartOffset(forwardOffset);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new RStartSideRightMiddleHatchPath(Path.Direction.FORWARD)),
                        new SeriesAction(Arrays.asList(
                                new WaitForPathFlagAction("down"),
                                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN)
                        ))
                )),

                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
                //Vision
                //new LineUpAction(false),
                // when true, ground outtake action
               /* new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathListAction(new RMiddleHatchStartRightHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false))
                )*/
        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
