package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.ElevatorController;

public class ElevatorAction implements Action{

    double currentHeight;
    double target;

    private ElevatorController elevator = null;
    private ElevatorController.ElevatorPresets elevatorState;
    public ElevatorAction(ElevatorController.ElevatorPresets ElevatorState){
        elevator = ElevatorController.getInstance();
    }

    @Override
    public void done(){

    }

    @Override
    public boolean isFinished(){
        return (target - 2 < currentHeight && target + 2 > currentHeight);

    }




    @Override
    public void start(){
        elevator.setTarget(elevatorState);
    }

    @Override
    public void update(){
        target = elevator.getElevatorPresetsHeights(elevatorState);
        currentHeight = elevator.getHeightInches();
    }

}
