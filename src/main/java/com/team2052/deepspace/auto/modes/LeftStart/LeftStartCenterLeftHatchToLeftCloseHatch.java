package com.team2052.deepspace.auto.modes.LeftStart;

        import com.team2052.deepspace.auto.AutoMode;
        import com.team2052.deepspace.auto.actions.FollowPathAction;
        import com.team2052.deepspace.auto.actions.HatchAction;
        import com.team2052.deepspace.auto.actions.HatchIntakeAction;
        import com.team2052.deepspace.auto.actions.SeriesAction;
        import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
        import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchPath;
        import com.team2052.deepspace.auto.paths.LeftStart.LStartCenterLeftHatchPath;
        import com.team2052.deepspace.auto.paths.Path;

        import java.util.Arrays;

public class LeftStartCenterLeftHatchToLeftCloseHatch extends AutoMode {
    @Override
    protected void init() {
        Path firstPath = new LStartCenterLeftHatchPath();
        Path secondPath = new CLeftHatchStartLeftHatchPickUpPath();
        Path thirdPath = new LHatchPickUpStartLeftCloseHatchPath();
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
