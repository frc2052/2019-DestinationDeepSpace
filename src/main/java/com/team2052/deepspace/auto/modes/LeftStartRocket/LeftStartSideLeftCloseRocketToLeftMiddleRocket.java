package com.team2052.deepspace.auto.modes.LeftStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftMiddleRocketPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftCloseRocketToLeftMiddleRocket extends AutoMode {
    @Override



    protected void init() {



        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftCloseHatchPath(Path.Direction.BACKWARD)),
               //Vision
                new LineUpAction(true),
                //placement of hatch when true
                new GroundIntakeAction(true),
                //Vision
                new LineUpAction(true),
                // change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartSideLeftMiddleRocketPath(Path.Direction.FORWARD))
        )));
    }
}
