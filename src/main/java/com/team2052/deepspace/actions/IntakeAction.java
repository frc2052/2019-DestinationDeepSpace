package com.team2052.deepspace.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.subsystems.IntakeController;

public class IntakeAction {
    private IntakeStateEnum state;
    IntakeController intake = IntakeController.getInstance();
    public IntakeAction(IntakeStateEnum state, double waitTimeOverRide){
        this.state = state;
    }

    public enum IntakeStateEnum {
        OUTTAKE,
        INTAKE,
    }
}