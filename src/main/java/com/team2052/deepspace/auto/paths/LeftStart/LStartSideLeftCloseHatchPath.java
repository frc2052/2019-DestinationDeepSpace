package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftCloseHatchPath extends Path {

    public LStartSideLeftCloseHatchPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(AutoModeSelector.getForwardOffset(),-47),60));
        addWaypoint(new Waypoint(new Position2d(50,-47), Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(170,-75),Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(194,-75),Constants.Autonomous.kTestVelocity));
        addWaypoint(new Waypoint(new Position2d(194,-65),Constants.Autonomous.kTestVelocity));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
