package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftFarHatchPath;

import java.util.Arrays;

public class CenterStartCenterLeftHatchToLeftFarHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //TODO: start with robot going backwards
                new FollowPathAction(new CStartCenterLeftHatchPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(new CLeftHatchStartLeftHatchPickUpPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //TODO: Change to compound path so robot turns around
                new FollowPathAction(new LHatchPickUpStartLeftFarHatchPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
                //TODO: Drive back to loading station
        )));
    }
}
