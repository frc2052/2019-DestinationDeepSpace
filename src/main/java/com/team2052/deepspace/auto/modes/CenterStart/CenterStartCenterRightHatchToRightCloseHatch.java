package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.FollowPathListAction;
import com.team2052.deepspace.auto.actions.HatchIntakeAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CRightHatchStartRightHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterRightHatchPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchCompoundPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class CenterStartCenterRightHatchToRightCloseHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //TODO: Make starting path start going backwards
                new FollowPathAction(new CStartCenterRightHatchPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(new CRightHatchStartRightHatchPickUpPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //TODO: This needs to be right, not left side compound path
                new FollowPathListAction(new LHatchPickUpStartLeftCloseHatchCompoundPath().getPaths()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
                //TODO: Drive back towards loading station
        )));
    }
}
