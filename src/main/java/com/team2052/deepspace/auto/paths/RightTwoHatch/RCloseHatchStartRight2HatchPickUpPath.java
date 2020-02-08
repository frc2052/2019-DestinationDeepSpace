package com.team2052.deepspace.auto.paths.RightTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RCloseHatchStartRight2HatchPickUpPath extends Path{

    public RCloseHatchStartRight2HatchPickUpPath() {
        setDirection(Direction.FORWARD);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(194,60),Constants.Autonomous.kHighGearAutoVelocity)); //110, 120
        addWaypoint(new Waypoint(new Position2d(70,120),Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(30,125),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(10,130),Constants.Autonomous.kHighGearAutoVelocity));
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
