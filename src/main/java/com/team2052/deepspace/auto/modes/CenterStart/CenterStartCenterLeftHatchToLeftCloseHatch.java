package com.team2052.deepspace.auto.modes.CenterStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.CenterHatchStarts.CLeftHatchStartLeftHatchPickUpPath;
import com.team2052.deepspace.auto.paths.CenterStart.CStartCenterLeftHatchPath;
import com.team2052.deepspace.auto.paths.CompoundPath;
import com.team2052.deepspace.auto.paths.HatchPickUp.LHatchPickUpStartLeftCloseHatchCompoundPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;
import java.util.List;

public class CenterStartCenterLeftHatchToLeftCloseHatch extends AutoMode {
    @Override
    protected void init() {
        //two hatch auto, the robot will drive backwards with hatch panel on ground pickup to start
        Path firstPath = new CStartCenterLeftHatchPath(Path.Direction.BACKWARD);
        Path secondPath = new CLeftHatchStartLeftHatchPickUpPath();
        //TODO: this path back to the CS needs to backup first, then another path to drive forward
        CompoundPath thirdPath = new LHatchPickUpStartLeftCloseHatchCompoundPath();
        runAction(new SeriesAction(Arrays.asList(
                new FollowPathAction(firstPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                new FollowPathAction(secondPath),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                new FollowPathListAction(thirdPath.getPaths()),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE)
        )));
    }
}
