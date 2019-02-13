package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterRightHatch extends AutoMode {
    private Action myAction;
    public CenterStartCenterRightHatch(){
        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.CENTER);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterRightHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new FollowPathListAction(new CRightHatchStartRightHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false))
                )));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
