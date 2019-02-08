package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LMiddleHatchStartLeftHatchPickUpPathCompoundPath extends CompoundPath {

    public  LMiddleHatchStartLeftHatchPickUpPathCompoundPath() {
        addPath(new  LMiddleHatchStartLeftHatchPickUpPath1());
        addPath(new  LMiddleHatchStartLeftHatchPickUpPath2());
    }

    class   LMiddleHatchStartLeftHatchPickUpPath1 extends Path {
        public  LMiddleHatchStartLeftHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(216, -48), 50)); //Q
            addWaypoint(new Waypoint(new Position2d(216, -65), 50)); // O
            addWaypoint(new Waypoint(new Position2d(194, -65), 50)); // N
            addWaypoint(new Waypoint(new Position2d(153, -65), 50)); // M
            addWaypoint(new Waypoint(new Position2d(111, -134), 50)); // U
            addWaypoint(new Waypoint(new Position2d(111, -154), 50)); // V
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  LMiddleHatchStartLeftHatchPickUpPath2 extends Path {
        public  LMiddleHatchStartLeftHatchPickUpPath2() {
            setDirection(Direction.FORWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(111, -134), 50)); // V
            addWaypoint(new Waypoint(new Position2d(111, -154), 50)); // U
            addWaypoint(new Waypoint(new Position2d(68, -134), 50)); // W
            addWaypoint(new Waypoint(new Position2d(29, -134), 50)); // E
            addWaypoint(new Waypoint(new Position2d(-66, -134), 50)); // D
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

