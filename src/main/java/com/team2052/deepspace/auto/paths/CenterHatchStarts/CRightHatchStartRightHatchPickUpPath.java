package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CRightHatchStartRightHatchPickUpPath extends Path{

    public CRightHatchStartRightHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(135,-10),50)); //k
        addWaypoint(new Waypoint(new Position2d(129,10),50)); //j
        addWaypoint(new Waypoint(new Position2d(68,47),50)); //g
        addWaypoint(new Waypoint(new Position2d(68,65  ),50)); //f
        addWaypoint(new Waypoint(new Position2d(29,134  ),50)); //e
        addWaypoint(new Waypoint(new Position2d(-66,134  ),50)); //d
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
