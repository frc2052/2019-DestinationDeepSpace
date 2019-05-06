package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPathTwoHatchCompoundPath;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartRightHatchPickUpPathTwoHatchCompoundPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartCenterLeftTwoHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseTwoHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartCenterRightTwoHatchPathCompoundPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class CenterLeftToLeftClose2Hatch extends AutoMode {

    public CenterLeftToLeftClose2Hatch(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new WaitAction(.15),
                new FollowPathListAction(new CLeftHatchStartLeftHatchPickUpPathTwoHatchCompoundPath().getPaths()),
                new DriverControlledAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new WaitAction(.15),
                new FollowPathListAction(new LHatchPickUpStartLeftCloseTwoHatchPathCompoundPath().getPaths()),
                new DriverControlledAction(false)

        )));
    }
}
