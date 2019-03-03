package com.team2052.deepspace.auto.paths.LeftStart;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LStartSideLeftFarRocketCompoundPath extends CompoundPath {

    public LStartSideLeftFarRocketCompoundPath(Position2d startPos) {
        addPath(new BackUpPath(startPos));
        addPath(new placeHatchPath());
    }

    class BackUpPath extends Path {
        public BackUpPath(Position2d startPos) {
            System.out.println("StartPos in Path: F: " + startPos.getForward() + " L: " + startPos.getLateral());
            setDirection(Direction.BACKWARD);
            //these dont match up because it moved via vision
            addWaypoint(new Waypoint(startPos, 50));
            addWaypoint(new Waypoint(new Position2d(55, -49), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(90, -105), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(244, -105), Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class placeHatchPath extends Path {
        public placeHatchPath() {
            setDirection(Direction.FORWARD);
            addWaypoint(new Waypoint(new Position2d(244,-105), 50));
            addWaypoint(new Waypoint(new Position2d(244,-113), Constants.Autonomous.kAutoVelocity));
            //addWaypoint(new Waypoint(new Position2d(230,-120), Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

