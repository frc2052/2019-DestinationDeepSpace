package com.team2052.deepspace.auto.modes.LeftStart;

        import com.team2052.deepspace.auto.AutoMode;
        import com.team2052.deepspace.auto.actions.FollowPathAction;
        import com.team2052.deepspace.auto.actions.FollowPathListAction;
        import com.team2052.deepspace.auto.actions.HatchIntakeAction;
        import com.team2052.deepspace.auto.actions.SeriesAction;
        import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
        import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchCompoundPath;
        import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
        import com.team2052.deepspace.auto.paths.Path;

        import java.util.Arrays;

public class LeftStartCenterLeftHatchToLeftCloseHatch extends AutoMode {
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //TODO: Make starting path start going backwards
                new FollowPathAction(new LStartCenterLeftHatchPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(new CLeftHatchStartLeftHatchPickUpPath()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new FollowPathListAction(new LHatchPickUpStartLeftCloseHatchCompoundPath().getPaths()),
                //TODO: Vision
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
                //TODO: Drive back towards loading station
        )));
    }
}
