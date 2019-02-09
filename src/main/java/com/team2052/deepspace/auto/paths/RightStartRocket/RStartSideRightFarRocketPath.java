package com.team2052.deepspace.auto.paths.RightStartRocket;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartSideRightFarRocketPath extends Path {

    public RStartSideRightFarRocketPath(Direction backwards) {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,-47),50)); //B
        addWaypoint(new Waypoint(new Position2d(68.125,-47),50)); //G
        addWaypoint(new Waypoint(new Position2d(68.125,65),50)); //F
        addWaypoint(new Waypoint(new Position2d(153.25,65),50)); //M
        addWaypoint(new Waypoint(new Position2d(172,65),50)); //X
        addWaypoint(new Waypoint(new Position2d(194.75,65),50)); //N
        addWaypoint(new Waypoint(new Position2d(215.5,65),50)); //O
        addWaypoint(new Waypoint(new Position2d(223,110),50)); //F1
        addWaypoint(new Waypoint(new Position2d(223,80),50)); //G1
        addWaypoint(new Waypoint(new Position2d(211,127),50)); //E1
        addWaypoint(new Waypoint(new Position2d(192,135),50)); //D1



        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
