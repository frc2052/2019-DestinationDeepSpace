package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LFarHatchStartLeftHatchPickUpPath extends Path{

    public LFarHatchStartLeftHatchPickUpPath() {
        setDirection(Direction.FORWARD);
        //TODO: determine paths to far hatch
        addWaypoint(new Waypoint(new Position2d(0,0),50));
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
