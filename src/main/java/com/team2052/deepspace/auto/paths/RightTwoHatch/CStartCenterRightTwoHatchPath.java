package com.team2052.deepspace.auto.paths.RightTwoHatch;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CStartCenterRightTwoHatchPath extends Path{

    public CStartCenterRightTwoHatchPath(Position2d startPos, Direction dir) {
        setDirection(dir);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(0,10),100));
        addWaypoint(new Waypoint(new Position2d(85,10),50, "end"));
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}