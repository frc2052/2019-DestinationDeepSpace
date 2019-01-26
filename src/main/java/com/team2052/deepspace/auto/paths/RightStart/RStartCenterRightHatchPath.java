package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartCenterRightHatchPath extends Path {

    public RStartCenterRightHatchPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,47),50)); //C
        addWaypoint(new Waypoint(new Position2d(68,10),50)); // h
        addWaypoint(new Waypoint(new Position2d(134,10),50)); // k
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
