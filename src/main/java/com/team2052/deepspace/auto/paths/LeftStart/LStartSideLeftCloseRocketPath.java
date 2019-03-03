package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftCloseRocketPath extends Path {

    public LStartSideLeftCloseRocketPath(Position2d startPos,Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(startPos,50)); //B
        addWaypoint(new Waypoint(new Position2d(55,-47), Constants.Autonomous.kAutoVelocity)); //G
        addWaypoint(new Waypoint(new Position2d(70,-100),Constants.Autonomous.kAutoVelocity)); //F
        addWaypoint(new Waypoint(new Position2d(90,-110),Constants.Autonomous.kAutoVelocity)); //V
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
