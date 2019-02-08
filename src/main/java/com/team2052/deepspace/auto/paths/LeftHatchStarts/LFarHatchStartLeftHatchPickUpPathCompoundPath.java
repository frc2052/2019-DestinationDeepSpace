package com.team2052.deepspace.auto.paths.LeftHatchStarts;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LFarHatchStartLeftHatchPickUpPathCompoundPath extends CompoundPath {

    public  LFarHatchStartLeftHatchPickUpPathCompoundPath() {
        addPath(new  LFarHatchStartLeftHatchPickUpPath1());
        addPath(new  LFarHatchStartLeftHatchPickUpPath2());
    }

    class   LFarHatchStartLeftHatchPickUpPath1 extends Path {
        public  LFarHatchStartLeftHatchPickUpPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: we need path points for far hatches!!
            //TODO: Identify points/paths the robot in order to turn around
            //TODO: ADD COORDINATES
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class  LFarHatchStartLeftHatchPickUpPath2 extends Path {
        public  LFarHatchStartLeftHatchPickUpPath2() {
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

