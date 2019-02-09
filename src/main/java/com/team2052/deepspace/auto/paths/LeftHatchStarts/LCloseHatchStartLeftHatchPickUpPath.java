package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LCloseHatchStartLeftHatchPickUpPath extends Path{

    public LCloseHatchStartLeftHatchPickUpPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(194,-47),100)); //P
        addWaypoint(new Waypoint(new Position2d(68,-65  ),100)); //F
        addWaypoint(new Waypoint(new Position2d(29,-125  ),100)); //E
        addWaypoint(new Waypoint(new Position2d(-30,-125  ),100)); //D
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
