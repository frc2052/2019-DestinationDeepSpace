package com.team2052.deepspace.auto.paths.CenterStart;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CStartCenterLeftHatchPath extends Path{

    public CStartCenterLeftHatchPath(Position2d startPos, Direction dir) {
        setDirection(dir);
        addWaypoint(new Waypoint(startPos,50)); //* T *
        addWaypoint(new Waypoint(new Position2d(50,0),50, "down")); //i
        addWaypoint(new Waypoint(new Position2d(80,-10),50)); //J
        addWaypoint(new Waypoint(new Position2d(94,-10),50, "end")); //K
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
