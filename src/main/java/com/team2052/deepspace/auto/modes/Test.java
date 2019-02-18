package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathListAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.NotSmoothTestCompoundPath;
import com.team2052.deepspace.auto.paths.SmoothTestCompoundPath;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(){
        setStartDirection(StartDirection.BACKWARD);
    }

    CompoundPath p1 = new NotSmoothTestCompoundPath();
    CompoundPath p2 = new SmoothTestCompoundPath();
    @Override
    protected void init() {

        System.out.println("init");

        action = new SeriesAction(Arrays.asList(

                new FollowPathListAction(p1.getPaths())
        ));
    }
}
