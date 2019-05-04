package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CRightHatchStartLeftHatchPickUpPathTwoHatchCompoundPath extends CompoundPath {

    public CRightHatchStartLeftHatchPickUpPathTwoHatchCompoundPath() {
        addPath(new  CLeftHatchStartLeftHatchPickUpPath1());
        addPath(new  CLeftHatchStartLeftHatchPickUpPath2());
    }

    class   CLeftHatchStartLeftHatchPickUpPath1 extends Path {
        public  CLeftHatchStartLeftHatchPickUpPath1() {
            isHighGear = true;
            setDirection(Direction.BACKWARD);
//            addWaypoint(new Waypoint(new Position2d(120,-3),100));
//            addWaypoint(new Waypoint(new Position2d(100,10), Constants.Autonomous.kHighGearAutoVelocity));
//            addWaypoint(new Waypoint(new Position2d(92,16), Constants.Autonomous.kHighGearAutoVelocity));
//            addWaypoint(new Waypoint(new Position2d(84,25), Constants.Autonomous.kHighGearAutoVelocity));
//            addWaypoint(new Waypoint(new Position2d(80,40), Constants.Autonomous.kHighGearAutoVelocity));
//            addWaypoint(new Waypoint(new Position2d(80,95),Constants.Autonomous.kHighGearAutoVelocity));
//            addWaypoint(new Waypoint(new Position2d(84,108),Constants.Autonomous.kHighGearAutoVelocity));
//            addWaypoint(new Waypoint(new Position2d(90,115),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(130,3),100));
            addWaypoint(new Waypoint(new Position2d(95,-20),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,-60),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,-80),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(90,-100),Constants.Autonomous.kHighGearAutoVelocity));
            //OptimizePath();
            forceQuickOptimization();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  CLeftHatchStartLeftHatchPickUpPath2 extends Path {
        public  CLeftHatchStartLeftHatchPickUpPath2() {
            setDirection(Direction.FORWARD);
            isHighGear = true;
            addWaypoint(new Waypoint(new Position2d(90,-110),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(30,-140),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(-10,-140),Constants.Autonomous.kHighGearAutoVelocity));
//            OptimizePath();
            forceQuickOptimization();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
