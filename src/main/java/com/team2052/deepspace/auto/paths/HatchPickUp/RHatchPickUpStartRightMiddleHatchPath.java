package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RHatchPickUpStartRightMiddleHatchPath extends Path{

    public RHatchPickUpStartRightMiddleHatchPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(-66,134),50)); // d
        addWaypoint(new Waypoint(new Position2d(29,134),50)); // e
        addWaypoint(new Waypoint(new Position2d(68,65),50)); // f
        addWaypoint(new Waypoint(new Position2d(215,65),50)); // o
        addWaypoint(new Waypoint(new Position2d(215,47),50)); // q
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
