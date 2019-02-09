package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.GroundIntakeController;

public class GroundIntakeAction implements Action {
    private GroundIntakeController controller;
    private boolean finished = false;
    private boolean intakeState; //Todo: remove this for ground intake
    public GroundIntakeAction(boolean state){
        intakeState = state;
        start();
    } //Most of the work is actually done in controller.

    public void done(){
        finished = true;
    }

    public boolean isFinished(){
        // Once Hatch is placed then we return arm to starting Postion
        if (!finished && controller.getPlacementComplete()) {
            finished = true;
            controller.setStartPos();
        }
        return finished;
    }

    public void start(){
        controller.placement(true);
    }

    public void update(){

    }
}
