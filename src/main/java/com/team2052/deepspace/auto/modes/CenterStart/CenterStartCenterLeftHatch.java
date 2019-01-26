package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterLeftHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new CStartCenterLeftHatchPath();
        //Path secondPath = new CLeftHatchStartLeftHatchPickUpPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //new FollowPathAction(secondPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
