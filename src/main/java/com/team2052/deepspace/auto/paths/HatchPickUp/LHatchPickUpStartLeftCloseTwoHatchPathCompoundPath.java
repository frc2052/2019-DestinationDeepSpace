package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LHatchPickUpStartLeftCloseTwoHatchPathCompoundPath extends CompoundPath {

    public LHatchPickUpStartLeftCloseTwoHatchPathCompoundPath() {
        addPath(new  RHatchPickUpStartRightCloseHatchPath1());
        addPath(new  RHatchPickUpStartRightCloseHatchPath2());
    }

    class   RHatchPickUpStartRightCloseHatchPath1 extends Path {
        public  RHatchPickUpStartRightCloseHatchPath1() {
            setDirection(Direction.BACKWARD);
            isHighGear = true;
            addWaypoint(new Waypoint(new Position2d(-30,-125), Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(60,-90),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(195,-70),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(195,-80  ),Constants.Autonomous.kHighGearAutoVelocity));
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

            addWaypoint(new Waypoint(new Position2d(195,-80),Constants.Autonomous.kHighGearAutoVelocity));
            addWaypoint(new Waypoint(new Position2d(195,-65),Constants.Autonomous.kHighGearAutoVelocity));
            forceQuickOptimization();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

