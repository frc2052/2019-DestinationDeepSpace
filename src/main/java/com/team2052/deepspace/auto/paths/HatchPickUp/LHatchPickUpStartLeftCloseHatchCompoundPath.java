package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.Constants;
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
            isHighGear = true;
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(-30,-125), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(60,-90),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(195,-70),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(195,-80  ),Constants.Autonomous.kAutoVelocity));
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
            addWaypoint(new Waypoint(new Position2d(195,-80),50));
            addWaypoint(new Waypoint(new Position2d(195,-65),50));

            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
