package com.team2052.deepspace.auto.paths.RightStart;

import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartCenterRightHatchPath extends Path {

    public RStartCenterRightHatchPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(AutoModeSelector.getForwardOffset(),47),50)); // B
        addWaypoint(new Waypoint(new Position2d(50,47),50)); // B
        addWaypoint(new Waypoint(new Position2d(100,7),50)); // H
        addWaypoint(new Waypoint(new Position2d(124,7),50)); // K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
