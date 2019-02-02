package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftCloseHatch extends AutoMode {

    public LeftStartSideLeftCloseHatch(){
        startDirection = StartDirection.FORWARD;
        startPosition = StartPosition.LEFT;
    }
    @Override
    protected void init() {
        Path firstPath = new LStartSideLeftCloseHatchPath();
        // Path secondPath = new LCloseHatchStartLeftHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath)
                //new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                // new FollowPathAction(secondPath),
                //new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)
        )));
    }
}
