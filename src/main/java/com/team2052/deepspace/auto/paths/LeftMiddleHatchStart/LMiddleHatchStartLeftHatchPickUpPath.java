package com.team2052.deepspace.auto.paths.LeftMiddleHatchStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LMiddleHatchStartLeftHatchPickUpPath extends Path{

    public LMiddleHatchStartLeftHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(216,-47),50)); //Q
        addWaypoint(new Waypoint(new Position2d(215,-65),50)); //O
        addWaypoint(new Waypoint(new Position2d(68,-65  ),50)); //F
        addWaypoint(new Waypoint(new Position2d(29,-134  ),50)); //E
        addWaypoint(new Waypoint(new Position2d(-66,-134  ),50)); //D
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
