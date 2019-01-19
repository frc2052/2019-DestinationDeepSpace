package com.team2052.deepspace.auto.paths.CenterRightHatchStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CRightHatchStartLeftHatchPickUpPath extends Path{

    public CRightHatchStartLeftHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,0),50));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
