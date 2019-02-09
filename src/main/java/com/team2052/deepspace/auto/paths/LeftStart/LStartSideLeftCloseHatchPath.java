package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftCloseHatchPath extends Path {

    public LStartSideLeftCloseHatchPath(Direction direction) {
        setDirection(direction);
        double i = 3.7;
        addWaypoint(new Waypoint(new Position2d(0,-47),50)); // B
        addWaypoint(new Waypoint(new Position2d(50,-47),25*i));
        addWaypoint(new Waypoint(new Position2d(110,-78),25*i));
        addWaypoint(new Waypoint(new Position2d(125,-83),25*i));
        addWaypoint(new Waypoint(new Position2d(142,-85),25*i));
        addWaypoint(new Waypoint(new Position2d(175,-85),25*i));
        addWaypoint(new Waypoint(new Position2d(188,-82),25*i));
        addWaypoint(new Waypoint(new Position2d(194,-70),25*i));
        addWaypoint(new Waypoint(new Position2d(194,-47),25*i)); // P
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
