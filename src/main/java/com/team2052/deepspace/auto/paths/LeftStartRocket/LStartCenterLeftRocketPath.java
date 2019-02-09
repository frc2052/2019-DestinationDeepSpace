package com.team2052.deepspace.auto.paths.LeftStartRocket;

import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartCenterLeftRocketPath extends Path{

    public LStartCenterLeftRocketPath(Direction backward) {
        setDirection(Direction.FORWARD);



        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
