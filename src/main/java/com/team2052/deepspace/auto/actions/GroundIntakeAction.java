package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.GroundIntakeController;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class GroundIntakeAction implements Action {
    private GroundIntakeController controller;
    private boolean finished = false;
    private boolean placingHatch;
    private double timeSinceDoneHatch;
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
        if (placingHatch) { //place the hatch
            controller.placement(true);
        }
        else {
            timeSinceDoneHatch = Timer.getFPGATimestamp(); //record when we started action, this should be the time when the robot started backing up
        }

    }
    @Override
    public void update(){
        if(!placingHatch && ((Timer.getFPGATimestamp() - timeSinceDoneHatch) > 2)){ //Wait 2 Seconds until raise the arm
            controller.setStartPos();
            finished = true; //we don't need to wait for the arm to go back up
        }
    }
}
