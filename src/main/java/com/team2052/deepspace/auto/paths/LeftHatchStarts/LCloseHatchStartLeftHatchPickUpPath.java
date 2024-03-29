package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LCloseHatchStartLeftHatchPickUpPath extends Path{

    public LCloseHatchStartLeftHatchPickUpPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(194,-47), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(194,-67), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(150,-125), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(29,-125  ),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(0,-125  ),Constants.Autonomous.kAutoVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
