package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RHatchPickUpBackUp extends Path {
    public RHatchPickUpBackUp(Path.Direction direction) {
        setDirection(direction);
        isHighGear = true;
        addWaypoint(new Waypoint(new Position2d(-20,130), Constants.Autonomous.kHighGearAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(190,100),Constants.Autonomous.kHighGearAutoVelocity, "down"));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }

}
