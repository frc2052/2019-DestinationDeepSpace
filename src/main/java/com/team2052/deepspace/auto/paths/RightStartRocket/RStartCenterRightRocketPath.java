package com.team2052.deepspace.auto.paths.RightStartRocket;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartCenterRightRocketPath extends Path {

    public RStartCenterRightRocketPath(Direction backwards) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,47),50)); // C
        addWaypoint(new Waypoint(new Position2d(68,65),50)); // f
        addWaypoint(new Waypoint(new Position2d(194,-47),50)); //c
        addWaypoint(new Waypoint(new Position2d(194,-47),50)); //f
        addWaypoint(new Waypoint(new Position2d(194,-47),50)); //w
        addWaypoint(new Waypoint(new Position2d(194,-47),50)); //u
        addWaypoint(new Waypoint(new Position2d(194,-47),50)); //v
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
