package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.IntakeController;
import com.team2052.lib.KnightTimer;

public class HatchIntakeAction implements Action {
    private hatchIntakeStateEnum state;
    private IntakeController intake = IntakeController.getInstance();
    private KnightTimer timer;
    private double startTime;


    public HatchIntakeAction(hatchIntakeStateEnum state){
        this.state = state; //When calling say whether to intake or outtake

    }

    @Override
    public void done(){
        timer = null;
        startTime = 0.0;
    }

    @Override
    public boolean isFinished(){
        return timer.getTime() - startTime > 1.0;
    }

    @Override
    public void start(){
        timer = new KnightTimer();
        startTime = timer.getTime();
    }

    @Override
    public void update(){
        System.out.println("HatchAction timer: " + (timer.getTime()-startTime));
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

    }

    public enum hatchIntakeStateEnum {
        OUTTAKE,
        INTAKE,
        ARMDOWN,
        ARMUP,
    }

}
