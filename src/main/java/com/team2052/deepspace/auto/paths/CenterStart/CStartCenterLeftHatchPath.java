package com.team2052.deepspace.auto.paths.CenterStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CStartCenterLeftHatchPath extends Path{

    public CStartCenterLeftHatchPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,0),50)); //* T *
        addWaypoint(new Waypoint(new Position2d(68,0),50)); //i
        addWaypoint(new Waypoint(new Position2d(129,-10),50)); //J
        addWaypoint(new Waypoint(new Position2d(135,-10),50)); //K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
