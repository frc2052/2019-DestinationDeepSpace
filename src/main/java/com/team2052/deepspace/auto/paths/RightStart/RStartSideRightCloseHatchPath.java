package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartSideRightCloseHatchPath extends Path {

    public RStartSideRightCloseHatchPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,47),50)); // C
        addWaypoint(new Waypoint(new Position2d(68,65),50)); // f
        addWaypoint(new Waypoint(new Position2d(193,65),50)); // n
        addWaypoint(new Waypoint(new Position2d(193,47),50)); // p
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
