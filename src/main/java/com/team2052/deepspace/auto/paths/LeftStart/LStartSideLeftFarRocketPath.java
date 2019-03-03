package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftFarRocketPath extends Path {

    public LStartSideLeftFarRocketPath(Position2d startPos, Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(startPos,50));
        addWaypoint(new Waypoint(new Position2d(55,-49), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(235,-49), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(250,-65), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(250,-110), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(230,-120), Constants.Autonomous.kAutoVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
