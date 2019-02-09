package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LHatchPickUpStartLeftMiddleHatchPathCompoundPath extends CompoundPath {

    public LHatchPickUpStartLeftMiddleHatchPathCompoundPath() {
        addPath(new LHatchPickUpStartLeftMiddleHatchPath1());
        addPath(new LHatchPickUpStartLeftMiddleHatchPath2());
    }

    class LHatchPickUpStartLeftMiddleHatchPath1 extends Path {
        public LHatchPickUpStartLeftMiddleHatchPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(-30,-125),100)); // D
            addWaypoint(new Waypoint(new Position2d(30,-134),100)); // E
            addWaypoint(new Waypoint(new Position2d(70,-70),100)); // E
            addWaypoint(new Waypoint(new Position2d(218,-80),100)); // E
            addWaypoint(new Waypoint(new Position2d(218,-110),100)); // E

            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class LHatchPickUpStartLeftMiddleHatchPath2 extends Path {
        public LHatchPickUpStartLeftMiddleHatchPath2() {
            setDirection(Direction.FORWARD);
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(218,-110),100));
            addWaypoint(new Waypoint(new Position2d(218,-47),100)); // Q
            OptimizePath();
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
