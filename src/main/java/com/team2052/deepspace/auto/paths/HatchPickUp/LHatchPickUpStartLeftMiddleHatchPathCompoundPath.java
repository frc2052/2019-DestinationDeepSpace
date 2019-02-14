package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.Constants;
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
            addWaypoint(new Waypoint(new Position2d(-30,-125), Constants.Autonomous.kTestVelocity));
            addWaypoint(new Waypoint(new Position2d(60,-70),Constants.Autonomous.kTestVelocity));
            addWaypoint(new Waypoint(new Position2d(216,-60),Constants.Autonomous.kTestVelocity));
            addWaypoint(new Waypoint(new Position2d(216,-80  ),Constants.Autonomous.kTestVelocity));
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
            addWaypoint(new Waypoint(new Position2d(216,-80),Constants.Autonomous.kTestVelocity));
            addWaypoint(new Waypoint(new Position2d(216,-55  ),Constants.Autonomous.kTestVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
