package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LCloseHatchStartLeftHatchPickUpPathCompoundPath extends CompoundPath {

    public  LCloseHatchStartLeftHatchPickUpPathCompoundPath() {
        addPath(new  LCloseHatchStartLeftHatchPickUpPath1());
        addPath(new  LCloseHatchStartLeftHatchPickUpPath2());
    }

    class   LCloseHatchStartLeftHatchPickUpPath1 extends Path {
        public  LCloseHatchStartLeftHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: Take coordinates from LHatchPickUpStartLeftFarHatchPath and add here, fix points to allow for a turn
            addWaypoint(new Waypoint(new Position2d(194,-47),50)); //P
            addWaypoint(new Waypoint(new Position2d(194,-65),50)); // N
            addWaypoint(new Waypoint(new Position2d(153,-65),50)); // M
            addWaypoint(new Waypoint(new Position2d(68,-47),50)); // G
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  LCloseHatchStartLeftHatchPickUpPath2 extends Path {
        public  LCloseHatchStartLeftHatchPickUpPath2() {
            setDirection(Direction.FORWARD);
            //TODO: Take coordinates from LHatchPickUpStartLeftFarHatchPath and add here, fix points to allow for a turn
            addWaypoint(new Waypoint(new Position2d(68,-47),50)); // G
            addWaypoint(new Waypoint(new Position2d(68,-65),50)); // F
            addWaypoint(new Waypoint(new Position2d(29,-134),50)); // E
            addWaypoint(new Waypoint(new Position2d(-66,-134),50)); // D


            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

