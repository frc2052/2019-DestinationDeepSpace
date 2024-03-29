package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftFarHatchPath extends Path {

    public LStartSideLeftFarHatchPath(Position2d startPos, Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(startPos,50));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
