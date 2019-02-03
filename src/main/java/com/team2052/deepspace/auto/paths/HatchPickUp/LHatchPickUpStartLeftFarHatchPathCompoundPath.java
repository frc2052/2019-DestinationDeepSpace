package com.team2052.deepspace.auto.paths.HatchPickUp;

import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
import com.team2052.lib.Autonomous.Waypoint;

import java.util.List;

public class LHatchPickUpStartLeftFarHatchPathCompoundPath extends CompoundPath {

    public LHatchPickUpStartLeftFarHatchPathCompoundPath() {
        addPath(new LHatchPickUpStartLeftFarHatchPath1());
        addPath(new LHatchPickUpStartLeftFarHatchPath2());
    }

    class LHatchPickUpStartLeftFarHatchPath1 extends Path {
        public LHatchPickUpStartLeftFarHatchPath1() {
            setDirection(Direction.BACKWARD);
            //TODO: find paths to far hatch
            //TODO: Make sure we have enough points for the robot to turn
            //TODO: Add coordinates
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

    class LHatchPickUpStartLeftFarHatchPath2 extends Path {
        public LHatchPickUpStartLeftFarHatchPath2() {
            setDirection(Direction.FORWARD);
            //TODO: find paths to far hatch
            //TODO: Make sure we have enough points for the robot to turn
            //TODO: Add coordinates
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

