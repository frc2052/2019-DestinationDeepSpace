package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.*;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(Position2d startPos){
        super(startPos);
        setStartDirection(StartDirection.BACKWARD);
    }

    CompoundPath p1 = new NotSmoothTestCompoundPath();
    CompoundPath p2 = new SmoothTestCompoundPath();

    @Override
    protected void init() {

        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");
        System.out.println("###########################################init###########################################");

        setAction(new SeriesAction(Arrays.asList(

                new FollowPathAction(new ForwardPath(Path.Direction.BACKWARD))
        )));
    }
}
