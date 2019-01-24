package com.team2052.deepspace.auto.paths.RightSideHatchStarts;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RMiddleHatchStartRightHatchPickUpPath extends Path{

    public RMiddleHatchStartRightHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(216,47),50)); //q
        addWaypoint(new Waypoint(new Position2d(216,65),50)); // o
        addWaypoint(new Waypoint(new Position2d(29,134),50)); // e
        addWaypoint(new Waypoint(new Position2d(-66,134),50)); // d
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
