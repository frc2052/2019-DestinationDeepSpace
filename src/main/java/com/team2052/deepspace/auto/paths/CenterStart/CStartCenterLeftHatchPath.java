package com.team2052.deepspace.auto.paths.CenterStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CStartCenterLeftHatchPath extends Path{

    public CStartCenterLeftHatchPath() {
        this(Direction.FORWARD);
    }

    public CStartCenterLeftHatchPath(Direction dir) {
        setDirection(dir);
        addWaypoint(new Waypoint(new Position2d(0,0),50)); //* T *
        addWaypoint(new Waypoint(new Position2d(50,0),50, "down")); //i
        addWaypoint(new Waypoint(new Position2d(100,-8),50)); //J
        addWaypoint(new Waypoint(new Position2d(116,-8),50, "end")); //K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
