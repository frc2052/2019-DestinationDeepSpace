package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartCenterLeftHatchPath extends Path{

    public LStartCenterLeftHatchPath(Position2d startPos, Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(startPos,50)); // B
        addWaypoint(new Waypoint(new Position2d(50,-47),50)); // B
        addWaypoint(new Waypoint(new Position2d(100,-3),50)); // H
        addWaypoint(new Waypoint(new Position2d(124,-3),50)); // K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
