package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LCloseHatchStartLeftHatchPickUpPath extends Path{

    public LCloseHatchStartLeftHatchPickUpPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(194,-47), Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(29,-125  ),Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(-30,-125  ),Constants.Autonomous.kTestVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
