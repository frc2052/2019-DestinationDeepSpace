package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RHatchPickUpStartRightFarHatchPathCompoundPath extends CompoundPath {

    public  RHatchPickUpStartRightFarHatchPathCompoundPath() {
        addPath(new  RHatchPickUpStartRightFarHatchPath1());
        addPath(new  RHatchPickUpStartRightFarHatchPath2());
    }

    class   RHatchPickUpStartRightFarHatchPath1 extends Path {
        public  RHatchPickUpStartRightFarHatchPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: Take coordinates from LHatchPickUpStartLeftFarHatchPath and add here, fix points to allow for a turn

            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  RHatchPickUpStartRightFarHatchPath2 extends Path {
        public  RHatchPickUpStartRightFarHatchPath2() {
            setDirection(Direction.FORWARD);
            //TODO: Take coordinates from LHatchPickUpStartLeftFarHatchPath and add here, fix points to allow for a turn

            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

