package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterToCenterLeft extends AutoMode {

    public CenterToCenterLeft() {
        super();
        setStartDirection(StartDirection.BACKWARD);
    }

    @Override
    protected void init() {
        action = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new CStartCenterLeftHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false))
                )));
    }
}
