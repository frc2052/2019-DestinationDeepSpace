package com.team2052.deepspace.auto.paths.LeftTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LCloseHatchBackup extends Path {

    public LCloseHatchBackup() {
        setDirection(Direction.BACKWARD);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(194,-47), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(194,-80),Constants.Autonomous.kAutoVelocity, "end")); //K
        forceQuickOptimization();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
