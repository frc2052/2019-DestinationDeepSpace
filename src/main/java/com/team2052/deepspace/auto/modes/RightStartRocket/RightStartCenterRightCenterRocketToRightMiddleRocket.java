package com.team2052.deepspace.auto.modes.RightStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.actions.LineUpAction;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStartRocket.RStartCenterRightRocketPath;
import com.team2052.deepspace.auto.paths.RightStartRocket.RStartSideRightMiddleRocketPath;

import java.util.Arrays;

public class RightStartCenterRightCenterRocketToRightMiddleRocket extends AutoMode {
    @Override



    protected void init() {



        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new RStartCenterRightRocketPath(Path.Direction.BACKWARD)),
               //Vision
                new LineUpAction(true),
               //change hatch action to GROUND hatch Intake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Vision
                new LineUpAction(true),
                // change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new RStartSideRightMiddleRocketPath(Path.Direction.FORWARD))
        )));
    }
}
