package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CLeftHatchStartLeftHatchPickUpPathCompoundPath extends CompoundPath {

    public  CLeftHatchStartLeftHatchPickUpPathCompoundPath() {
        addPath(new  CLeftHatchStartLeftHatchPickUpPath1());
        addPath(new  CLeftHatchStartLeftHatchPickUpPath2());
    }

    class   CLeftHatchStartLeftHatchPickUpPath1 extends Path {
        public  CLeftHatchStartLeftHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            addWaypoint(new Waypoint(new Position2d(100,-3),50));
            addWaypoint(new Waypoint(new Position2d(90,-5), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,-15), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,-100),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(90,-115),Constants.Autonomous.kAutoVelocity));

            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  CLeftHatchStartLeftHatchPickUpPath2 extends Path {
        public  CLeftHatchStartLeftHatchPickUpPath2() {
            setDirection(Direction.FORWARD);
            addWaypoint(new Waypoint(new Position2d(90,-115),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(30,-127),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(0,-127),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
