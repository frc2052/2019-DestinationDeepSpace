package com.team2052.deepspace.auto.modes.LeftHatchPickUp;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpBackUp;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftMiddleHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class LeftSecondHatchLeftMiddle extends AutoMode {
    public LeftSecondHatchLeftMiddle(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                        new FollowPathListAction(new LHatchPickUpStartLeftMiddleHatchPathCompoundPath().getPaths())
                ))
        )));
    }
}
