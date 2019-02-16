package com.team2052.deepspace.auto.modes.LeftStart;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.paths.LeftStart.LStartSideLeftCloseHatchPath;
import com.team2052.deepspace.auto.paths.Path;

import java.util.Arrays;

public class LeftStartSideLeftCloseHatch extends AutoMode {
    private Action myAction;
    public LeftStartSideLeftCloseHatch(){
        super();
        setStartDirection(StartDirection.FORWARD);
        setLateralStartPosition(LateralStartPosition.LEFT);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards

                new FollowPathAction(new LStartSideLeftCloseHatchPath(Path.Direction.FORWARD)),
                //new LineUpAction(5, true),
                //Vision
                new CargoIntakeAction(CargoIntakeAction.cargoIntakeStateEnum.OUTTAKE,0)
                // when true, ground outtake action
                /*new GroundIntakeAction(true),
                new ParallelAction(Arrays.asList(
                        //Turns robot around and drives back towards loading station
                        new FollowPathListAction(new LCloseHatchStartLeftHatchPickUpPathCompoundPath().getPaths()),
                        new GroundIntakeAction(false)
                ))*/


        ));
    }
    @Override
    protected void init() {
        runAction(myAction);
    }
}
