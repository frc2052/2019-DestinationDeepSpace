package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CRightHatchStartRightHatchPickUpPathCompoundPath extends CompoundPath {

    public CRightHatchStartRightHatchPickUpPathCompoundPath() {
        addPath(new  CRightHatchStartRightHatcPickUpPath1());
        addPath(new  CRightHatchStartRightHatcPickUpPath2());
    }

    class   CRightHatchStartRightHatcPickUpPath1 extends Path {
        public  CRightHatchStartRightHatcPickUpPath1() {
            setDirection(Direction.BACKWARD);
            addWaypoint(new Waypoint(new Position2d(120,3),50));
            addWaypoint(new Waypoint(new Position2d(100,10), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(92,16), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(84,25), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,40), Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,95),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(84,108),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(90,115),Constants.Autonomous.kAutoVelocity));

            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  CRightHatchStartRightHatcPickUpPath2 extends Path {
        public  CRightHatchStartRightHatcPickUpPath2() {
            setDirection(Direction.FORWARD);
            addWaypoint(new Waypoint(new Position2d(90,115),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(30,140),Constants.Autonomous.kAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(0,140),Constants.Autonomous.kAutoVelocity));
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
