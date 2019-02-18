package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartSideRightMiddleHatchPath extends Path {

    public RStartSideRightMiddleHatchPath(Position2d startPos, Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(startPos,60));
        addWaypoint(new Waypoint(new Position2d(50,47), Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(170,75),Constants.Autonomous.kTestVelocity, "down"));
        addWaypoint(new Waypoint(new Position2d(215,75),Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(215,65),Constants.Autonomous.kTestVelocity,"end"));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
