package com.team2052.deepspace.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.subsystems.IntakeController;
import edu.wpi.first.wpilibj.Timer;

public class CargoIntakeAction implements Action {
    private double intakeTimer;
    private double outtakeTimer;
    private double intakeDelay;
    private boolean finished = false;
    private cargoIntakeStateEnum state;
    private IntakeController intake = IntakeController.getInstance();
    public CargoIntakeAction(cargoIntakeStateEnum state, double intakeDelay){
        this.state = state;
        this.intakeDelay = intakeDelay;
    }

    public void done(){
        intake.setNeutralCargoSpeed();
        finished = true;
    }

    public boolean isFinished(){
        return finished;
    }

    public void start(){
        switch(state) {
            case OUTTAKE:
                outtakeTimer = Timer.getFPGATimestamp();
            case INTAKE:
                intakeTimer = Timer.getFPGATimestamp();
        }
    }

    public void update(){
        switch(state) {
            case OUTTAKE:
                intake.outtake();
                if(Timer.getFPGATimestamp() - outtakeTimer >= intakeDelay) {
                    intake.setCargoIntakeState(false);
                    done();
                }
            case INTAKE:
                intake.intake();
                if(Timer.getFPGATimestamp() - intakeTimer >= intakeDelay) {
                    intake.setCargoIntakeState(true);
                    done();
                }
        }

    }
    public enum cargoIntakeStateEnum {
        OUTTAKE,
        INTAKE,
    }
}