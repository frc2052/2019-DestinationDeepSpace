package com.team2052.deepspace.actions;

import com.team2052.deepspace.subsystems.IntakeController;
import edu.wpi.first.wpilibj.Timer;

public class CargoIntakeAction implements Action {
    private double intakeTimer;
    private double outtakeTimer;
    private double delay;
    private boolean finished = false;
    private cargoIntakeStateEnum state;
    private IntakeController intake = IntakeController.getInstance();
    public CargoIntakeAction(cargoIntakeStateEnum state, double delay){
        this.state = state;
        this.delay = delay;
    }

    public void done(){
        intake.cargoNeutral();
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
                intake.cargoOuttake();
                if((Timer.getFPGATimestamp() - outtakeTimer >= delay) && intake.getCargoIntakeState()) {
                    intake.setCargoIntakeState(false);
                    done();
                }
            case INTAKE:
                intake.cargoIntake();
                if((Timer.getFPGATimestamp() - intakeTimer >= delay) && !intake.getCargoIntakeState()) {
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