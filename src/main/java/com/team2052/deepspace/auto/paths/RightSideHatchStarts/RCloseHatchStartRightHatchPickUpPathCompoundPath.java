package com.team2052.deepspace.auto.paths.RightSideHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RCloseHatchStartRightHatchPickUpPathCompoundPath extends CompoundPath {

    public  RCloseHatchStartRightHatchPickUpPathCompoundPath() {
        addPath(new  RCloseHatchStartRightHatchPickUpPath1());
        addPath(new  RCloseHatchStartRightHatchPickUpPath2());
    }

    class   RCloseHatchStartRightHatchPickUpPath1 extends Path {
        public  RCloseHatchStartRightHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(194, 48), 50)); //p
            addWaypoint(new Waypoint(new Position2d(194, 65), 50)); // n
            addWaypoint(new Waypoint(new Position2d(153, 65), 50)); // m
            addWaypoint(new Waypoint(new Position2d(111, 134), 50)); // u
            addWaypoint(new Waypoint(new Position2d(111, 154), 50)); // v
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  RCloseHatchStartRightHatchPickUpPath2 extends Path {
        public  RCloseHatchStartRightHatchPickUpPath2() {
            setDirection(Direction.FORWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(111, 134), 50)); // v
            addWaypoint(new Waypoint(new Position2d(111, 154), 50)); // u
            addWaypoint(new Waypoint(new Position2d(68, 134), 50)); // w
            addWaypoint(new Waypoint(new Position2d(29, 134), 50)); // e
            addWaypoint(new Waypoint(new Position2d(-66, 134), 50)); // d
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

