package com.team2052.deepspace.auto.modes.LeftStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.actions.VisionAction;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftFarRocketPath;
import com.team2052.deepspace.auto.paths.LeftStartRocket.LStartSideLeftMiddleRocketPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftFarRocketToLeftMiddleRocket extends AutoMode {
    @Override



    protected void init() {



        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartSideLeftFarRocketPath(Path.Direction.BACKWARD)),
               //Vision
                new VisionAction(),
               //change hatch action to GROUND hatch Intake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Vision
                new VisionAction(),
                //change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartSideLeftMiddleRocketPath(Path.Direction.FORWARD))
        )));
    }
}
