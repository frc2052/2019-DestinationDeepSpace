package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftCloseHatchPath extends Path {

    public LStartSideLeftCloseHatchPath(Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(new Position2d(0,-47),70)); // B
        addWaypoint(new Waypoint(new Position2d(50,-47),70));
        addWaypoint(new Waypoint(new Position2d(130,-75),70)); // F
        addWaypoint(new Waypoint(new Position2d(175,-75),70));
        addWaypoint(new Waypoint(new Position2d(188,-72),70));
        addWaypoint(new Waypoint(new Position2d(194,-60),70));
        addWaypoint(new Waypoint(new Position2d(194,-47),70)); // P
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
