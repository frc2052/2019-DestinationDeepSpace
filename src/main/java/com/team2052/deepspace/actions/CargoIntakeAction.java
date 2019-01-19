package com.team2052.deepspace.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.subsystems.IntakeController;
import edu.wpi.first.wpilibj.Timer;


public class CargoIntakeAction implements Action {
    private double intakeTimer;
    private double outtakeTimer;
    private boolean finished = false;
    private cargoIntakeStateEnum state;
    IntakeController intake = IntakeController.getInstance();
    public CargoIntakeAction(cargoIntakeStateEnum state, double waitTimeOverRide){
        this.state = state;
    }

    public void done(){

    }

    public boolean isFinished(){
        intake.cargoIntake(0);
        return finished = true;
    }

    public void start(){
        switch(state) {
            case OUTTAKE:
                outtakeTimer = Timer.getFPGATimestamp();
            case INTAKE:
                intakeTimer = Timer.getFPGATimestamp();
            case FINISHED:
        }
    }

    public void update(){
        switch(state) {
            case OUTTAKE:
                intake.cargoIntake(Constants.Intake.kOuttakePercentSpeed);
                if(Timer.getFPGATimestamp() - outtakeTimer >= Constants.Intake.kIntakeDelay) {
                    intake.setCargoIntakeState(false);
                    if(Timer.getFPGATimestamp() - (2*outtakeTimer) >= Constants.Intake.kIntakeDelay) {
                        isFinished();
                    }
                }
            case INTAKE:
                 intake.cargoIntake(Constants.Intake.kIntakePercentSpeed);
                if(Timer.getFPGATimestamp() - intakeTimer >= Constants.Intake.kIntakeDelay) {
                    intake.setCargoIntakeState(true);
                    if(Timer.getFPGATimestamp() - (2*intakeTimer) >= Constants.Intake.kIntakeDelay) {
                        isFinished();
                    }
                }
            case FINISHED:
        }

    }
    public enum cargoIntakeStateEnum {
        FINISHED,
        OUTTAKE,
        INTAKE,
    }
}