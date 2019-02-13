package com.team2052.deepspace.auto.modes.RightStartRocket;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.actions.*;
import com.team2052.deepspace.auto.modes.RightStart.RightStartSideRightMiddleHatchToRightFarHatch;
import com.team2052.deepspace.auto.paths.Path;
import com.team2052.deepspace.auto.paths.RightStartRocket.RStartSideRightFarRocketPath;
import com.team2052.deepspace.auto.paths.RightStartRocket.RStartSideRightMiddleRocketPath;

import java.util.Arrays;

public class RightStartSideRightMiddleRocketToRightFarRocket extends AutoMode {
    private Action myAction;
    public RightStartSideRightMiddleRocketToRightFarRocket(){

        super();
        setStartDirection(StartDirection.BACKWARD);
        setLateralStartPosition(LateralStartPosition.CENTER);

        myAction = new SeriesAction(Arrays.asList(
                //Starting path starts going backwards
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new RStartSideRightMiddleRocketPath(Path.Direction.BACKWARD)),
                //Vision
                new LineUpAction(false),
                //change hatch action to GROUND hatch Intake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.INTAKE),
                //Vision
                new LineUpAction(true),
                // change hatch action to GROUND hatch outtake
                new HatchIntakeAction(HatchIntakeAction.hatchIntakeStateEnum.OUTTAKE),
                //Drives back towards loading station
                //:Update Paths To Its Actual One For the MODE
                new FollowPathAction(new RStartSideRightFarRocketPath(Path.Direction.FORWARD))
        ));
    }
    @Override



    protected void init() {
        runAction(myAction);
    }
}
