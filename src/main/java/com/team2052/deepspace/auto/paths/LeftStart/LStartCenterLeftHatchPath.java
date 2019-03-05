package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartCenterLeftHatchPath extends Path{

    public LStartCenterLeftHatchPath(Position2d startPos, Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(startPos,60));
        addWaypoint(new Waypoint(new Position2d(55,-47), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(85,-6),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(94,-7),Constants.Autonomous.kAutoVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
