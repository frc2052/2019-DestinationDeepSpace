package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftCloseHatchPath extends Path {

    public LStartSideLeftCloseHatchPath(Position2d startPos, Direction direction) {
        setDirection(direction);
        addWaypoint(new Waypoint(startPos,80));
        addWaypoint(new Waypoint(new Position2d(50,-47), Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(160,-100),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(170,-92),Constants.Autonomous.kAutoVelocity));
        addWaypoint(new Waypoint(new Position2d(188,-84),Constants.Autonomous.kAutoVelocity));//188, -84
        addWaypoint(new Waypoint(new Position2d(186,-75),Constants.Autonomous.kAutoVelocity)); //185 -75
        OptimizePath();
    }

    @Override
    public List<Waypoint> getWaypoints() {
        return wayPoints;
    }
}
