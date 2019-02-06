package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LHatchPickUpStartLeftCloseHatchCompoundPath extends CompoundPath {

    public LHatchPickUpStartLeftCloseHatchCompoundPath() {
        addPath(new LHatchPickUpStartLeftCloseHatchPath1());
        addPath(new LHatchPickUpStartLeftCloseHatchPath2());
    }

    class LHatchPickUpStartLeftCloseHatchPath1 extends Path {
        public LHatchPickUpStartLeftCloseHatchPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(-66,-134),50)); // D
            addWaypoint(new Waypoint(new Position2d(29,-134),50)); // E
            addWaypoint(new Waypoint(new Position2d(68,-65),50)); // F
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class LHatchPickUpStartLeftCloseHatchPath2 extends Path {
        public LHatchPickUpStartLeftCloseHatchPath2() {
            setDirection(Direction.FORWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(68,-65),50)); // F
            addWaypoint(new Waypoint(new Position2d(194,-65),50)); // N
            addWaypoint(new Waypoint(new Position2d(194,-47),50)); // P
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
