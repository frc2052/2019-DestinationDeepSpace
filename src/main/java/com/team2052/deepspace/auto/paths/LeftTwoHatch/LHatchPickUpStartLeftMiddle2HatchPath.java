package com.team2052.deepspace.auto.paths.LeftTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;


public class LHatchPickUpStartLeftMiddle2HatchPath extends Path {
    public LHatchPickUpStartLeftMiddle2HatchPath() {
        setDirection(Direction.BACKWARD);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(-30,-125), Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(0,-120),Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(200,-75),Constants.Autonomous.kHighGearAutoVelocity));
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }

}



