package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.*;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
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

                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                new FollowPathAction(new TestPath03())
               // new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
