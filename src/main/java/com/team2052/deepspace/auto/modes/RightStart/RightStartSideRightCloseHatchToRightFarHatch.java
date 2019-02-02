package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpStartRightFarHatchPath;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RCloseHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;

import java.util.Arrays;

public class RightStartSideRightCloseHatchToRightFarHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new RStartSideRightCloseHatchPath();
        Path secondPath = new RCloseHatchStartRightHatchPickUpPath();
        Path thirdPath = new RHatchPickUpStartRightFarHatchPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(secondPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new FollowPathAction(thirdPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
