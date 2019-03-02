package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LeftCargoShipBackupPath extends Path {

    public LeftCargoShipBackupPath() {
        setDirection(Direction.BACKWARD);
        addWaypoint(new Waypoint(new Position2d(194,-65),50)); //K
        addWaypoint(new Waypoint(new Position2d(194,-100),50, "end")); //K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
