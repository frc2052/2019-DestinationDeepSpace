package com.team2052.deepspace.actions;

import com.team2052.deepspace.subsystems.ElevatorController;

public class ElevatorAction {

    private ElevatorController elevator = null;

    public ElevatorAction(ElevatorController.ElevatorPresets ElevatorState){
        elevator = ElevatorController.getInstance();
    }


    public void done(){

    }

    public boolean isFinished(){
        double target = elevator.getElevatorPresetsHeights(elevatorState);
        double currentHeight = elevator.getHeightInches();
        return (target - 2 < currentHeight && target + 2 > currentHeight);

    }
    private ElevatorController.ElevatorPresets elevatorState;



    public void start(){
        elevator.setTarget(elevatorState);
    }

    public void update(){

    }

   /* public enum ElevatorPresets {
        GROUND_CARGO,
        HATCH_LEVEL1,
        HATCH_LEVEL2,
        HATCH_LEVEL3,
        CARGOSHIP_CARGO,
        ROCKET_CARGO1,
        ROCKET_CARGO2,
        ROCKET_CARGO3,

    }*/
}
