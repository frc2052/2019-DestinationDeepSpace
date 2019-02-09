package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.ForwardPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.TestBackPath01;

import java.util.Arrays;

public class Test extends AutoMode {

    public Test(){
        setStartDirection(StartDirection.BACKWARD);
        setStartPosition(StartPosition.CENTER);
    }

    @Override
    protected void init() {

        System.out.println("init");

        runAction(new SeriesAction(Arrays.asList(

                new FollowPathAction(new ForwardPath(Path.Direction.BACKWARD)),
                new FollowPathAction(new TestBackPath01(Path.Direction.FORWARD))
                //new FollowPathAction(backwardPath)
        )));
    }
}
