package com.team2052.deepspace.auto.modes.RightStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.HatchPickUp.RHatchPickUpBackUp;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightSideHatchStarts.RCloseHatchStartRightHatchPickUpPathCompoundPath;
import com.team2052.deepspace.auto.paths.RightStart.RStartSideRightCloseHatchPath;
import com.team2052.lib.Autonomous.Position2d;

import java.util.Arrays;

public class ForwardRightToRightClose extends AutoMode {
    public ForwardRightToRightClose(Position2d startPos){
        super(startPos);
        setStartDirection(AutoMode.StartDirection.FORWARD);
    }
    @Override
    protected void init() {
        setAction(new SeriesAction(Arrays.asList(
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.ARMDOWN),
                        new FollowPathAction(new RStartSideRightCloseHatchPath(startingPos, Path.Direction.FORWARD))
                )),
                //Vision
                new VisionAction(true),
                // when true, ground outtake action
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
//                //Turns robot around and drives back towards loading station
                new ParallelAction(Arrays.asList(
                        new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                        new FollowPathListAction(new RCloseHatchStartRightHatchPickUpPathCompoundPath().getPaths())
                )),

                new VisionAction(true),
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE)

        )));
    }

}