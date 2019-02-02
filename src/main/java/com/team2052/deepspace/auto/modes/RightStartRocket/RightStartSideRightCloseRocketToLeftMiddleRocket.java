package com.team2052.deepspace.auto.modes.RightStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.actions.VisionAction;
import com.team2052.deepspace.auto.paths.LeftHatchStarts.LCloseHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class RightStartSideRightCloseRocketToLeftMiddleRocket extends AutoMode {
    @Override



    protected void init() {



        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                //TODO:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LStartSideLeftCloseHatchPath(Path.Direction.BACKWARD)),
               //Vision
                new VisionAction(),
               //change hatch action to GROUND hatch Intake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Vision
                new VisionAction(),
                // change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //TODO:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new LCloseHatchStartLeftHatchPickUpPath())
        )));
    }
}
