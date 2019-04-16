package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CLeftHatchStartLeftHatchPickUpPath extends Path{

    public CLeftHatchStartLeftHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(120,-3),50)); //K
        addWaypoint(new Waypoint(new Position2d(100,-10), Constants.Autonomous.kAutoVelocity)); //G
        addWaypoint(new Waypoint(new Position2d(68,-65),Constants.Autonomous.kAutoVelocity)); //F
        addWaypoint(new Waypoint(new Position2d(29, -127), Constants.Autonomous.kAutoVelocity)); //E
        addWaypoint(new Waypoint(new Position2d(0,-127),Constants.Autonomous.kAutoVelocity)); //D
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
