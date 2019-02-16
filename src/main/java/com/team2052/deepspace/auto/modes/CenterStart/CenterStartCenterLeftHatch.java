package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.Action;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterLeftHatch extends AutoMode {
    private Action myAction = null;
    public CenterStartCenterLeftHatch() {
        super();
        setStartDirection(StartDirection.FORWARD);
        setLateralStartPosition(LateralStartPosition.CENTER);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterLeftHatchPath(Path.Direction.FORWARD))//,
                //Vision
                //new LineUpAction(false),
                // when true, ground outtake action
                //new GroundIntakeAction(true),
                //Turns robot around and drives back towards loading station
                /*new ParallelAction(Arrays.asList(
                        new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false)
                ))*/
        ));
    }

    @Override
    protected void init() {
        runAction(myAction);
    }
}
