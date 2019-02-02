package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftCloseHatchPathFromHab2Test extends Path {

    public LStartSideLeftCloseHatchPathFromHab2Test() {
        setDirection(Direction.FORWARD);
        addWaypoint(new Waypoint(new Position2d(0,-47+6.5),70)); // B
        addWaypoint(new Waypoint(new Position2d(50+45,-47 + 6.5),70));
        addWaypoint(new Waypoint(new Position2d(68+45,-65 + 6.5),70)); // F
        addWaypoint(new Waypoint(new Position2d(194+45,-65+6.5),70)); // N
        addWaypoint(new Waypoint(new Position2d(194+45,-47+6.5),70)); // P
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
