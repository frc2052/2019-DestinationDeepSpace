package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartCenterRightHatchPath extends Path {

    public RStartCenterRightHatchPath(Position2d startPos, Direction direction) {
        System.out.println("StaringPos " + startPos.getForward() + " " + startPos.getLateral());
        setDirection(direction);
        addWaypoint(new Waypoint(startPos,50)); // B
        addWaypoint(new Waypoint(new Position2d(55,47),50)); // B
        addWaypoint(new Waypoint(new Position2d(85,3),50)); // H
        addWaypoint(new Waypoint(new Position2d(94,3),50)); // K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
