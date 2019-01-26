package com.team2052.deepspace.actions;

import com.team2052.deepspace.subsystems.GroundIntakeController;

public class GroundIntakeAction implements Action {
    private GroundIntakeController controller;
    private boolean finished = false;
    private boolean intakeState;
    public GroundIntakeAction(boolean state){
        intakeState = state;
        start();
    } //Most of the work is actually done in controller.

    public void done(){
        finished = true;
    }

    public boolean isFinished(){
        return finished;
    }

    public void start(){
        if(intakeState) {
            controller.groundIntake(true);
        }
        else controller.groundIntake(false);
    }

    public void update(){
        if(intakeState) {
            if (controller.groundIntakeState()) {//True by default unless groundintake is run
                done();
            }
        } else {
            if(controller.groundOuttakeState()){//True by default unless groundintake is run
                done();
            }
        }
    }



}
