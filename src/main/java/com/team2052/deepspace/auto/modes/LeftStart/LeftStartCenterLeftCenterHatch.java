package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.Action;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartCenterLeftCenterHatch extends AutoMode {
    private Action myAction;
    public LeftStartCenterLeftCenterHatch(){
        super();
        setStartDirection(StartDirection.FORWARD);
        setLateralStartPosition(LateralStartPosition.LEFT);
        //setForwardStartOffset(forwardOffset);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartCenterLeftHatchPath(Path.Direction.FORWARD))//,
                //Vision
                //new LineUpAction(false),
                // when true, ground outtake action
                //new GroundIntakeAction(true),
                //Turns robot around and drives back towards loading station
               // new ParallelAction(Arrays.asList(
                       // new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathCompoundPath().getPaths())//,
                        //new GroundIntakeAction(false)
                //))
        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
