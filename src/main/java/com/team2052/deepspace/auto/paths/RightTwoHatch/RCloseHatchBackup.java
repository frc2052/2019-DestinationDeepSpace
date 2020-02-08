package com.team2052.deepspace.auto.paths.RightTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RCloseHatchBackup extends Path {

    public RCloseHatchBackup() {
        setDirection(Direction.BACKWARD);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(194,47), Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(194,60),Constants.Autonomous.kHighGearAutoVelocity, "end")); //K
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
