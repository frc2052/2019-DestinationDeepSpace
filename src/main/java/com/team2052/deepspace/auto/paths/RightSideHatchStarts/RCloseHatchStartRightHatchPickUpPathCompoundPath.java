package com.team2052.deepspace.auto.paths.RightSideHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class RCloseHatchStartRightHatchPickUpPathCompoundPath extends CompoundPath {

    public  RCloseHatchStartRightHatchPickUpPathCompoundPath() {
        addPath(new  RCloseHatchStartRightHatchPickUpPath1());
        addPath(new  RCloseHatchStartRightHatchPickUpPath2());
    }

    class   RCloseHatchStartRightHatchPickUpPath1 extends Path {
        public  RCloseHatchStartRightHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: ADD COORDINATES
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  RCloseHatchStartRightHatchPickUpPath2 extends Path {
        public  RCloseHatchStartRightHatchPickUpPath2() {
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

