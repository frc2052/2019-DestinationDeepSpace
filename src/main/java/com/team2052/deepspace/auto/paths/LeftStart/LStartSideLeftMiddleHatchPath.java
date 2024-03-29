package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftMiddleHatchPath extends Path {

    public LStartSideLeftMiddleHatchPath(Position2d startPos, Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(startPos,50)); // B
        addWaypoint(new Waypoint(new Position2d(68,-65),50)); // F
        addWaypoint(new Waypoint(new Position2d(194,-65),50)); // O
        addWaypoint(new Waypoint(new Position2d(194,-47),50)); // P
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
