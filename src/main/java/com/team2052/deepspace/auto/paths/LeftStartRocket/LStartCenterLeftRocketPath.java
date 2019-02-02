package com.team2052.deepspace.auto.paths.LeftStartRocket;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartCenterLeftRocketPath extends Path{

    public LStartCenterLeftRocketPath(Direction backward) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,-47),50)); // B
        addWaypoint(new Waypoint(new Position2d(68,-10),50)); // H
        addWaypoint(new Waypoint(new Position2d(68,-10),50)); // K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
