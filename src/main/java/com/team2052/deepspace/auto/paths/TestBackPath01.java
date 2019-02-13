package com.team2052.deepspace.auto.paths;

import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class TestBackPath01 extends Path{

    public TestBackPath01(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(96,0),120));
        addWaypoint(new Waypoint(new Position2d(0,0),120));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
