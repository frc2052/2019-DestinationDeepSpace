package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.TestPath03;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(){
        startDirection = StartDirection.BACKWARD;
    }

    @Override
    protected void init() {

        System.out.println("init");

        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new TestPath03())
                //new FollowPathAction(backwardPath)
        )));
    }
}
