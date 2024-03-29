package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LHatchPickUpStartLeftMiddleHatchPath extends Path{

    public LHatchPickUpStartLeftMiddleHatchPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(-66,-134),50)); // D
        addWaypoint(new Waypoint(new Position2d(29,-134),50)); // E
        addWaypoint(new Waypoint(new Position2d(68,-65),50)); // F
        addWaypoint(new Waypoint(new Position2d(215,-65),50)); // O
        addWaypoint(new Waypoint(new Position2d(215,-47),50)); // Q
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
