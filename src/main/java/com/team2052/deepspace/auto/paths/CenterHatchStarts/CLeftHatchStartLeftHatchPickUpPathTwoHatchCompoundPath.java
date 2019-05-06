package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class CLeftHatchStartLeftHatchPickUpPathTwoHatchCompoundPath extends CompoundPath {

    public CLeftHatchStartLeftHatchPickUpPathTwoHatchCompoundPath() {
        addPath(new  CLeftHatchStartLeftHatchPickUpPath1());
        addPath(new  CLeftHatchStartLeftHatchPickUpPath2());
    }

    class   CLeftHatchStartLeftHatchPickUpPath1 extends Path {
        public  CLeftHatchStartLeftHatchPickUpPath1() {
            isHighGear = true;
            setDirection(Direction.BACKWARD);

            addWaypoint(new Waypoint(new Position2d(130,-3),100));
            addWaypoint(new Waypoint(new Position2d(95,-20),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,-60),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(80,-80),Constants.Autonomous.kHighGearAutoVelocity)); //80, 120?
            addWaypoint(new Waypoint(new Position2d(90,-100),Constants.Autonomous.kHighGearAutoVelocity)); //110, 130?
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
            addWaypoint(new Waypoint(new Position2d(90,-110),Constants.Autonomous.kHighGearAutoVelocity)); //110, 120
            addWaypoint(new Waypoint(new Position2d(30,-140),Constants.Autonomous.kHighGearAutoVelocity)); //20,140
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
