package com.team2052.deepspace.auto.paths.RightSideHatchStarts;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.lib.Autonomous.Position2d;
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
            addWaypoint(new Waypoint(new Position2d(194, 48), Constants.Autonomous.kAutoVelocity)); //P
            addWaypoint(new Waypoint(new Position2d(200,20),Constants.Autonomous.kAutoVelocity)); // N
            addWaypoint(new Waypoint(new Position2d(220,65),Constants.Autonomous.kAutoVelocity)); // M
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
            addWaypoint(new Waypoint(new Position2d(220, 65), Constants.Autonomous.kAutoVelocity)); // V
            addWaypoint(new Waypoint(new Position2d(120, 65), Constants.Autonomous.kAutoVelocity)); // U
            addWaypoint(new Waypoint(new Position2d(30,134),Constants.Autonomous.kAutoVelocity)); // E
            addWaypoint(new Waypoint(new Position2d(0,134),Constants.Autonomous.kAutoVelocity)); // D
            OptimizePath();
        }

        @Override
        public List<Waypoint> getWaypoints() {
            return wayPoints;
        }

    }

}

