package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.TestPath02;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(){
        startDirection = StartDirection.FORWARD;
    }

    @Override
    protected void init() {

        System.out.println("init");

        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(new TestPath02())
                //new FollowPathAction(backwardPath)
        )));
    }
}
