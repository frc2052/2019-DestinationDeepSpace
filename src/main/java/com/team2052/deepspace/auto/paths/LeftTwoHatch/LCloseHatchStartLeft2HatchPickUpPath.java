package com.team2052.deepspace.auto.paths.LeftTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LCloseHatchStartLeft2HatchPickUpPath extends Path{

    public LCloseHatchStartLeft2HatchPickUpPath() {
        setDirection(Direction.FORWARD);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(194,-80),Constants.Autonomous.kHighGearAutoVelocity)); //110, 120
        addWaypoint(new Waypoint(new Position2d(70,-110),Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(30,-125),Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(0,-130),Constants.Autonomous.kHighGearAutoVelocity));
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
