package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartSideRightMiddleHatchPath extends Path {

    public RStartSideRightMiddleHatchPath(Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,47),50)); // C
        addWaypoint(new Waypoint(new Position2d(68,65),50)); // f
        addWaypoint(new Waypoint(new Position2d(216,65),50)); // o
        addWaypoint(new Waypoint(new Position2d(216,47),50)); //q
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
