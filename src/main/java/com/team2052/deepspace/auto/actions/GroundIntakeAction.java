package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.GroundIntakeController;
import edu.wpi.first.wpilibj.Timer;

public class GroundIntakeAction implements Action {
    private GroundIntakeController controller;
    private boolean finished = false;
    private boolean placingHatch;
    private double timeSinceStartAction;
    public GroundIntakeAction(boolean placeHatch){
        placingHatch = placeHatch;
    } //Most of the work is actually done in controller.

    public void done(){
        finished = true;
    }

    public boolean isFinished(){
        // Once Hatch is placed then we return arm to starting Postion
        if (!finished && controller.getPlacementComplete()) {//we will always be finished if we are putting the arm back into start
            finished = true;
        }
        return finished;
    }
    @Override
    public void start(){
        timeSinceStartAction = Timer.getFPGATimestamp();
        if (placingHatch) { //place the hatch
            controller.setWantState(GroundIntakeController.IntakeState.PLACEMENT);
        }
    }
    @Override
    public void update(){
        if (placingHatch) {
            if(Timer.getFPGATimestamp() - timeSinceStartAction > .5) {
                controller.setGrabberOpen(true);
            }
        } else { //dropping off
            if ((Timer.getFPGATimestamp() - timeSinceStartAction) > 2) { //Wait 2 Seconds until raise the arm
                controller.setWantState(GroundIntakeController.IntakeState.STARTING);
                finished = true; //we don't need to wait for the arm to go back up
            }
        }
    }
}
