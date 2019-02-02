package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LMiddleHatchStartLeftHatchPickUpPathCompoundPath extends CompoundPath {

    public  LMiddleHatchStartLeftHatchPickUpPathCompoundPath() {
        addPath(new  LMiddleHatchStartLeftHatchPickUpPath1());
        addPath(new  LMiddleHatchStartLeftHatchPickUpPath2());
    }

    class   LMiddleHatchStartLeftHatchPickUpPath1 extends Path {
        public  LMiddleHatchStartLeftHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: ADD COORDINATES
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  LMiddleHatchStartLeftHatchPickUpPath2 extends Path {
        public  LMiddleHatchStartLeftHatchPickUpPath2() {
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

