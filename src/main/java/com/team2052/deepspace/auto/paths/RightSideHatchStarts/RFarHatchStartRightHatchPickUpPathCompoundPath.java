package com.team2052.deepspace.auto.paths.RightSideHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RFarHatchStartRightHatchPickUpPathCompoundPath extends CompoundPath {

    public  RFarHatchStartRightHatchPickUpPathCompoundPath() {
        addPath(new  RFarHatchStartRightHatchPickUpPath1());
        addPath(new  RFarHatchStartRightHatchPickUpPath2());
    }

    class   RFarHatchStartRightHatchPickUpPath1 extends Path {
        public  RFarHatchStartRightHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: ADD COORDINATES
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  RFarHatchStartRightHatchPickUpPath2 extends Path {
        public  RFarHatchStartRightHatchPickUpPath2() {
            setDirection(Direction.FORWARD);
            //TODO: ADD COORDINATES
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

