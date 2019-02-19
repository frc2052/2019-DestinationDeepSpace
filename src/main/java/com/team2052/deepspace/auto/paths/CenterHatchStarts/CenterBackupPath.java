package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CenterBackupPath extends Path {

    public CenterBackupPath() {
        setDirection(Direction.BACKWARD);
        addWaypoint(new Waypoint(new Position2d(116,0),50)); //K
        addWaypoint(new Waypoint(new Position2d(75,0),50, "end")); //K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
