package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;

import java.util.Arrays;

public class RightStartSideRightCloseHatch extends AutoMode {
    private Action myAction;
    public RightStartSideRightCloseHatch(){
        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.RIGHT);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartSideRightCloseHatchPath(Path.Direction.BACKWARD)),
                //Vision
                //new LineUpAction(5,false),
                // when true, ground outtake action
                new GroundIntakeAction(true)//,
                /*
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathListAction(new RCloseHatchStartRightHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false)
                ))*/
        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
