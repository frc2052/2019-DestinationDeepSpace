package com.team2052.deepspace.auto.modes.LeftStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartCenterLeftRocketPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartCenterLeftCenterRocket extends AutoMode {
    @Override

    protected void init() {


/*
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartCenterLeftRocketPath(Path.Direction.BACKWARD)),
               //Vision
                new LineUpAction(true),
               //TODO: change hatch action to GROUND hatch Intake
                //when true, ground hatch places the hatch
                new GroundIntakeAction(true),
                //Vision
                new LineUpAction(true),
                // TODO: change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //TODO: Update Paths To Its Actual One For the MODE
                //TODO: write paths from all rockets back to loading station
                new FollowPathAction(new CLeftRocketHatchStartLeftHatchPickUpPath)

        )));
        */
    }
}
