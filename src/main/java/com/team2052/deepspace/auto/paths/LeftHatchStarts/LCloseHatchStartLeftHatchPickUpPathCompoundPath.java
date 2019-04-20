package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.Constants;
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
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(185, -65), Constants.Autonomous.kAutoVelocity)); //P
            addWaypoint(new Waypoint(new Position2d(200,-90),Constants.Autonomous.kAutoVelocity)); // N
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
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(200, -90), Constants.Autonomous.kAutoVelocity)); // V
            addWaypoint(new Waypoint(new Position2d(120, -100), Constants.Autonomous.kAutoVelocity)); // U
            addWaypoint(new Waypoint(new Position2d(30,-120),Constants.Autonomous.kAutoVelocity)); // E
            addWaypoint(new Waypoint(new Position2d(0,-120),Constants.Autonomous.kAutoVelocity)); // D
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

