package com.team2052.deepspace.auto.paths.CenterStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CStartCenterRightHatchPath extends Path{

    public CStartCenterRightHatchPath(Position2d startPos, Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(startPos,50)); // *T*
        addWaypoint(new Waypoint(new Position2d(50,0),50, "down")); // i
        addWaypoint(new Waypoint(new Position2d(100,14),50)); // j
        addWaypoint(new Waypoint(new Position2d(110,14),50, "end")); // k
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
