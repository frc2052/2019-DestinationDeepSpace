package com.team2052.deepspace.actions;

import com.team2052.deepspace.subsystems.IntakeController;

public class HatchIntakeAction implements Action {
    private boolean finished = false;
    private hatchIntakeStateEnum state;
    IntakeController intake = IntakeController.getInstance();
    public HatchIntakeAction(hatchIntakeStateEnum state){
        this.state = state; //When calling say whether to intake or outtake
    }

    public void done(){
        finished = true;
    }

    public boolean isFinished(){
        return finished; //Requirement of Action
    }

    public void start(){

    }

    public void update(){
        switch(state) {
            case OUTTAKE:
                    intake.sethatchIntakeState(false);
                    done();//Releases hatch, and waits before reporting it's done.

            case INTAKE:
                    intake.sethatchIntakeState(true);
                    done();
        }

    }
    public enum hatchIntakeStateEnum {
        OUTTAKE,
        INTAKE,
    }

}
