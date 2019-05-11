package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.NotSmoothTestCompoundPath;
import com.team2052.deepspace.auto.paths.SmoothTestCompoundPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.FORWARD);
    }

    CompoundPath p1 = new NotSmoothTestCompoundPath();
    CompoundPath p2 = new SmoothTestCompoundPath();

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");

       setAction(new SeriesAction(Arrays.asList(
//                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
//                new FollowPathAction(new LStartSideLeftClose2HatchPath(startingPos, Path.Direction.FORWARD)),
//                new DriverControlledAction(false),
//                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
//                new WaitAction(.15),
//                new FollowPathAction(new LCloseHatchBackup()),
//                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 180),
//                new FollowPathAction(new LCloseHatchStartLeft2HatchPickUpPath()),
//                new DriverControlledAction(true),
//                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
//                new WaitAction(.15),
//                new FollowPathAction(new LHatchPickUpStartLeftMiddle2HatchPath()),
//                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 90),
//                new DriverControlledAction(false)
//
////        new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 90),
//                new DriverControlledAction(false),
//                new TurnInPlaceAction(TurnInPlaceAction.TurnMode.FIELDCENTRIC, 0)

        )));
    }
}
