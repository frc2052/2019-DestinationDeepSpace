package com.team2052.deepspace.auto.paths.CenterStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CStartCenterRightHatchPath extends Path{

    public CStartCenterRightHatchPath(Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,0),50)); // *T*
        addWaypoint(new Waypoint(new Position2d(50,0),50)); // i
        addWaypoint(new Waypoint(new Position2d(100,13),50)); // j
        addWaypoint(new Waypoint(new Position2d(124,13),50)); // k
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
