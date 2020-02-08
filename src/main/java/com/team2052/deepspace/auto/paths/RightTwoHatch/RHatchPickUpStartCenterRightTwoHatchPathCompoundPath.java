package com.team2052.deepspace.auto.paths.RightTwoHatch;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RHatchPickUpStartCenterRightTwoHatchPathCompoundPath extends CompoundPath {

    public RHatchPickUpStartCenterRightTwoHatchPathCompoundPath() {
        addPath(new  RHatchPickUpStartRightCloseHatchPath1());
        //addPath(new  RHatchPickUpStartRightCloseHatchPath2());
    }

    class   RHatchPickUpStartRightCloseHatchPath1 extends Path {
        public  RHatchPickUpStartRightCloseHatchPath1() {
            setDirection(Direction.BACKWARD);
            isHighGear = true;
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(-30,134),100)); // d
            addWaypoint(new Waypoint(new Position2d(0,134),Constants.Autonomous.kHighGearAutoVelocity)); // d
            addWaypoint(new Waypoint(new Position2d(80,60),Constants.Autonomous.kHighGearAutoVelocity)); // e
            addWaypoint(new Waypoint(new Position2d(80,35),Constants.Autonomous.kHighGearAutoVelocity)); // f
            forceQuickOptimization();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  RHatchPickUpStartRightCloseHatchPath2 extends Path {
        public  RHatchPickUpStartRightCloseHatchPath2() {
            setDirection(Direction.FORWARD);
            isHighGear = true;
            //TODO: these points need to be fixed to allow for a turn
            addWaypoint(new Waypoint(new Position2d(80,10), Constants.Autonomous.kHighGearAutoVelocity)); // f
           // addWaypoint(new Waypoint(new Position2d(85,10),Constants.Autonomous.kHighGearAutoVelocity)); // n
            addWaypoint(new Waypoint(new Position2d(100,10),Constants.Autonomous.kHighGearAutoVelocity)); // p
            forceQuickOptimization();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
