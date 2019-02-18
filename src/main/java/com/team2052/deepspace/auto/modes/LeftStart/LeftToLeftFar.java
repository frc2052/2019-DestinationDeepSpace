package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LFarHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftFarHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftToLeftFar extends AutoMode {

    public LeftToLeftFar(){
        super();
        setStartDirection(StartDirection.BACKWARD);

    }
    @Override
    protected void init() {
        action  = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftFarHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathListAction(new LFarHatchStartLeftHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false))
                )

        ));
    }
}
