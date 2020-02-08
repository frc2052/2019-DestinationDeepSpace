package com.team2052.deepspace.auto.paths.RightTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RStartSideRightClose2HatchPath extends Path {

    public RStartSideRightClose2HatchPath(Position2d startPos, Direction direction) {
        setDirection(direction);
        isHighGear = true;
        addWaypoint(new Waypoint(startPos,80));
        addWaypoint(new Waypoint(new Position2d(50,47), Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(140,65), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(160,65),0)); //185 -75
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
