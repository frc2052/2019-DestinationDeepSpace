package com.team2052.deepspace.auto.modes;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.lib.Autonomous.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

public class Test2 extends AutoMode{


    @Override
    protected void init() {
        Path testPath = new Path();
        Path testBPath = new Path();

        System.out.println("init");
        testPath.addWaypoint(new Waypoint(new Position2d(0,0), 60));
        testPath.addWaypoint(new Waypoint(new Position2d(48,0), 60));
        testPath.addWaypoint(new Waypoint(new Position2d(48,-48), 60));
        testPath.addWaypoint(new Waypoint(new Position2d(96,-48), 60));
        testPath.addWaypoint(new Waypoint(new Position2d(96,0), 60));
        testPath.addWaypoint(new Waypoint(new Position2d(144,0), 60));
        runAction(new FollowPathAction(testPath, FollowPathAction.Direction.FORWARD));

        testBPath.addWaypoint(new Waypoint(new Position2d(144,0), 60));
        testBPath.addWaypoint(new Waypoint(new Position2d(0,0), 60));
        runAction(new FollowPathAction(testBPath, FollowPathAction.Direction.BACKWARD));
    }
}
