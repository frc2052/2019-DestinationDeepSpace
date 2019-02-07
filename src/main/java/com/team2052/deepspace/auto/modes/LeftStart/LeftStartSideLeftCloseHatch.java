package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.FollowPathAction;
import com.team2052.deepspace.auto.actions.SeriesAction;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftCloseHatch extends AutoMode {

    public LeftStartSideLeftCloseHatch(){
        startDirection = StartDirection.BACKWARD;
        startPosition = StartPosition.LEFT;
    }
    @Override
    protected void init() {
        runAction(new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                new FollowPathAction(new LStartSideLeftCloseHatchPath(Path.Direction.BACKWARD))//,
                //Vision
                //new VisionAction(),
                //TODO: change hatch action to GROUND hatch outtake
                //new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station

        )));
    }
}
