package com.team2052.deepspace.auto.modes.LeftStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.actions.LineUpAction;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartCenterLeftRocketPath;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftFarRocketPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartCenterLeftCenterRocketToLeftFarRocket extends AutoMode {
    @Override



    protected void init() {



        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartCenterLeftRocketPath(Path.Direction.BACKWARD)),
               //Vision
                new LineUpAction(),
               //change hatch action to GROUND hatch Intake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Vision
                new LineUpAction(),
                // change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartSideLeftFarRocketPath(Path.Direction.FORWARD))
        )));
    }
}
