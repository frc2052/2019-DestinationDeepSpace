package com.team2052.deepspace.auto.modes.LeftStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftCloseRocketPath;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftMiddleRocketPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftMiddleRocketToLeftCloseRocket extends AutoMode {
    @Override



    protected void init() {



        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftMiddleRocketPath(Path.Direction.BACKWARD)),
               //Vision
                new LineUpAction(),
                //placement of hatch when true
                new GroundIntakeAction(true),
                //Vision
                new LineUpAction(),
                //change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartSideLeftCloseRocketPath(Path.Direction.FORWARD))
        )));
    }
}
