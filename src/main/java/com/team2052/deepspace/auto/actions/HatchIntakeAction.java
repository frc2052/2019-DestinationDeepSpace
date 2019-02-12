package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.IntakeController;

public class HatchIntakeAction implements Action {
    private boolean finished = false;
    private hatchIntakeStateEnum state;
    private IntakeController intake = IntakeController.getInstance();
    public HatchIntakeAction(hatchIntakeStateEnum state){
        this.state = state; //When calling say whether to intake or outtake
    }

    @Override
    public void done(){
        finished = true;
    }

    @Override
    public boolean isFinished(){
        return finished; //Requirement of Action
    }

    @Override
    public void start(){
        switch(state) {
            case INTAKE:
                intake.setHatchPlace(false);
                break;
            case OUTTAKE:
                intake.setHatchPlace(true);
                break;
            case ARMDOWN:
                intake.setArmDown(true);
                break;
            case ARMUP:
                intake.setArmDown(false);
                break;
        }
        finished = true;
    }

    @Override
    public void update(){

    }

    public enum hatchIntakeStateEnum {
        OUTTAKE,
        INTAKE,
        ARMDOWN,
        ARMUP,
    }

}
