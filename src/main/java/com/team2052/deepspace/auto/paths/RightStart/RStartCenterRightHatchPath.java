package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartCenterRightHatchPath extends Path {

    public RStartCenterRightHatchPath(Position2d startPos, Direction direction) {
        System.out.println("StaringPos " + startPos.getForward() + " " + startPos.getLateral());
        setDirection(direction);
        addWaypoint(new Waypoint(startPos,80)); // B
        addWaypoint(new Waypoint(new Position2d(55,47), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(63,35),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(85,14),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(94,16),Constants.Autonomous.kAutoVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
