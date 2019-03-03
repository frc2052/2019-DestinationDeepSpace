package com.team2052.deepspace.auto.paths.LeftStartRocket;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LRocketCloseStartHatchPickUpCompoundPath extends CompoundPath {

    public LRocketCloseStartHatchPickUpCompoundPath() {
        addPath(new BackUpPath());
        addPath(new GrabHatchPath());
    }

    class BackUpPath extends Path {
        public BackUpPath() {
            setDirection(Direction.BACKWARD);
            //these dont match up because it moved via vision
            addWaypoint(new Waypoint(new Position2d(120, -120), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(70, -100), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(60, -90), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(60, -75), Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class GrabHatchPath extends Path {
        public GrabHatchPath() {
            setDirection(Direction.FORWARD);
            addWaypoint(new Waypoint(new Position2d(60, -75), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(30, -130), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(0, -130), Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

