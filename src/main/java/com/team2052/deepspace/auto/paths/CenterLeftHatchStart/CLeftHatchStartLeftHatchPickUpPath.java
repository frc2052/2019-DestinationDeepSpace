package com.team2052.deepspace.auto.paths.CenterLeftHatchStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CLeftHatchStartLeftHatchPickUpPath extends Path{

    public CLeftHatchStartLeftHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(135,-10),50)); //K
        addWaypoint(new Waypoint(new Position2d(68,-47),50)); //G
        addWaypoint(new Waypoint(new Position2d(68,-65),50)); //F
        addWaypoint(new Waypoint(new Position2d(26,-134),50)); //E
        addWaypoint(new Waypoint(new Position2d(-66,-134),50)); //D
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
