package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStart.RStartCenterRightHatchPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class RightToCenterRight extends AutoMode {

    public RightToCenterRight(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new RStartCenterRightHatchPath(startingPos, Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathListAction(new CRightHatchStartRightHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false)
                ))
        )));
    }
}
