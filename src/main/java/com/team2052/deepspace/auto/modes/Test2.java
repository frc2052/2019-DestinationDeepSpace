package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.TestBackPath02;
import com.team2052.deepspace.auto.paths.TestPath02;

import java.util.Arrays;

public class Test2 extends AutoMode {


    @Override
    protected void init() {
        Path testPath = new TestPath02();
        Path testBPath = new TestBackPath02();

        System.out.println("init");

        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(testPath),
                new FollowPathAction(testBPath)
        )));
    }
}
