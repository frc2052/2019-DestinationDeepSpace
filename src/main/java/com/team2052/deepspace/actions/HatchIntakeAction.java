package com.team2052.deepspace.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.subsystems.IntakeController;
import edu.wpi.first.wpilibj.Timer;


public class HatchIntakeAction implements Action {
    private double intakeTimer;
    private double outtakeTimer;
    private boolean finished = false;
    private hatchIntakeStateEnum state;
    IntakeController intake = IntakeController.getInstance();
    public HatchIntakeAction(hatchIntakeStateEnum state){
        this.state = state; //When calling say whether to intake or outtake
    }

    public void done(){

    }

    public boolean isFinished(){
        return finished = true; //Requirement of Action
    }

    public void start(){

    }

    public void update(){
        switch(state) {
            case OUTTAKE:
                    intake.sethatchIntakeState(false);
                    if(Timer.getFPGATimestamp() - (2*outtakeTimer) >= Constants.Intake.kIntakeDelay) {
                        isFinished(); //Releases hatch, and waits before reporting it's done.
                    }

            case INTAKE:
                    intake.sethatchIntakeState(true);
                    if(Timer.getFPGATimestamp() - (2*intakeTimer) >= Constants.Intake.kIntakeDelay) {
                        isFinished();
                    }

            case FINISHED:
        }

    }
    public enum hatchIntakeStateEnum {
        FINISHED,
        OUTTAKE,
        INTAKE,
    }

}
