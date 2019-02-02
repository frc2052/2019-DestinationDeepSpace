package com.team2052.deepspace.actions;

import com.team2052.deepspace.subsystems.IntakeController;

public class HatchIntakeAction implements Action {
    private boolean finished = false;
    private hatchIntakeStateEnum state;
    private IntakeController intake = IntakeController.getInstance();

    public HatchIntakeAction(hatchIntakeStateEnum state) {
        this.state = state; //When calling say whether to intake or outtake
    }

    public void done() {
        finished = true;
    }

    public boolean isFinished() {
        return finished; //Requirement of Action
    }

    public void start() {
        switch (state) {
            case INTAKE:
                if (!intake.getHatchIntakeState())
                    intake.setHatchIntakeState(true);
                done();
            case OUTTAKE:
                intake.setHatchIntakeState(false);
                done();//Releases hatch
        }
    }

    public void update() {

    }

    public enum hatchIntakeStateEnum {
        OUTTAKE,
        INTAKE,
    }

}
