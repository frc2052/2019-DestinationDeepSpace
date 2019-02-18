package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftToLeftClose extends AutoMode {

    public LeftToLeftClose(){
        super();
        setStartDirection(StartDirection.BACKWARD);
    }
    @Override
    protected void init() {
        action  = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftCloseHatchPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(18,false),
                // when true, ground outtake action
                new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathListAction(new LCloseHatchStartLeftHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false))
                )


        ));
    }
}
