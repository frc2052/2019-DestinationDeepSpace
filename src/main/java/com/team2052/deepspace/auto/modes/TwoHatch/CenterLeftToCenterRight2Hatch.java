package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartRightHatchPickUpPathTwoHatchCompoundPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartCenterRightTwoHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class CenterLeftToCenterRight2Hatch extends AutoMode {

    public CenterLeftToCenterRight2Hatch(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new WaitAction(.15),
                new FollowPathListAction(new CLeftHatchStartRightHatchPickUpPathTwoHatchCompoundPath().getPaths()),
                new DriverControlledAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(.15),
                new FollowPathListAction(new RHatchPickUpStartCenterRightTwoHatchPathCompoundPath().getPaths()),
                new DriverControlledAction(false)

        )));
    }
}
