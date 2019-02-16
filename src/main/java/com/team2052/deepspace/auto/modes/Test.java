package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.*;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(){
        setStartDirection(StartDirection.FORWARD);
        setLateralStartPosition(LateralStartPosition.CENTER);
    }

    CompoundPath p1 = new NotSmoothTestCompoundPath();
    CompoundPath p2 = new SmoothTestCompoundPath();
    @Override
    protected void init() {

        System.out.println("init");

        runAction(new SeriesAction(Arrays.asList(
                /*
                new ParallelAction(Arrays.asList(
                        new FollowPathAction(new ForwardPath(Path.Direction.FORWARD)),
                        new SeriesAction(Arrays.asList(
                                new WaitForPathFlagAction("down"),
                                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN)
                        ))
                )),

                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
                */
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN)
                //new FollowPathListAction(p1.getPaths())
        )));
    }
}
