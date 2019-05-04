package com.team2052.deepspace.auto.modes.TwoHatch;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartRightHatchPickUpPathTwoHatchCompoundPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftTwoHatchPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartCenterRightTwoHatchPathCompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardCenterRightToCenterLeft extends AutoMode {

    public ForwardCenterRightToCenterLeft(Position2d startPos){
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
                new FollowPathAction(new CStartCenterLeftTwoHatchPath(startingPos, Path.Direction.FORWARD)),
                new DriverControlledAction(false),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathListAction(new CLeftHatchStartRightHatchPickUpPathTwoHatchCompoundPath().getPaths()),
                new DriverControlledAction(true),
                new ParallelAction(Arrays.asList(
                        new FollowPathListAction(new RHatchPickUpStartCenterRightTwoHatchPathCompoundPath().getPaths()),
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
                )),
                new DriverControlledAction(false)

        )));
    }
}
