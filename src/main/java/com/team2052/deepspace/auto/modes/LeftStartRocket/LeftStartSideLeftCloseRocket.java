package com.team2052.deepspace.auto.modes.LeftStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftCloseRocketPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftCloseRocket extends AutoMode {
    @Override



    protected void init() {

        //new Path firstPath = rocketPath1;

        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftCloseRocketPath(Path.Direction.BACKWARD)),
               //Vision
                new LineUpAction(true),
                //placement of hatch when true
                new GroundIntakeAction(true),
                new LineUpAction(true),
                //TODO: change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
                //Drives back towards loading station

        )));
    }
}
