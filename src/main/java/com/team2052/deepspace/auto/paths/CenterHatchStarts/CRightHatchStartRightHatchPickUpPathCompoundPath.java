package com.team2052.deepspace.auto.paths.CenterHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
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
            //TODO: ADD COORDINATES
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
            //TODO: ADD COORDINATES
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}
