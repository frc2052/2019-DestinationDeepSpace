package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.LineUpAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.ForwardPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class Test extends AutoMode {


    @Override
    protected void init() {
        Path forwardPath = new ForwardPath();

        System.out.println("init");

        runAction(new SeriesAction(Arrays.asList(
                new LineUpAction()
                //new FollowPathAction(backwardPath)
        )));
    }
}
