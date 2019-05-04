package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartLeftHatchPickUpPathTwoHatchCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightTwoHatchPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartCenterLeftTwoHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardCenterLeftToCenterRight extends AutoMode {

    public ForwardCenterLeftToCenterRight(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(
                //new DriverControlledAction(),
                //new PrintAction("Hi")
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                new FollowPathAction(new CStartCenterRightTwoHatchPath(startingPos, Path.Direction.FORWARD)),
                new DriverControlledAction(false),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathListAction(new CRightHatchStartLeftHatchPickUpPathTwoHatchCompoundPath().getPaths()),
                new DriverControlledAction(true),
                new ParallelAction(Arrays.asList(
                        new FollowPathListAction(new LHatchPickUpStartCenterLeftTwoHatchPathCompoundPath().getPaths()),
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
                )),
                new DriverControlledAction(false)

        )));
    }
}
