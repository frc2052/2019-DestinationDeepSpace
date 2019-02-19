package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.IntakeController;
import edu.wpi.first.wpilibj.Timer;

public class HatchIntakeAction implements Action {
    private hatchIntakeStateEnum state;
    private IntakeController intake = IntakeController.getInstance();
//    private KnightTimer timer;
    private double startTime;

//    private boolean finished = false;

    public HatchIntakeAction(hatchIntakeStateEnum state){
        this.state = state; //When calling say whether to intake or outtake
    }

    @Override
    public void done(){
  //      timer = null;
        startTime = 0.0;
    }

    @Override
    public boolean isFinished(){
//        return finished;
        double timePasssed = Timer.getFPGATimestamp() - startTime;
        System.out.println("Time Passed " + timePasssed);
        return timePasssed > 1.0;
    }

    @Override
    public void start(){
//        timer = new KnightTimer();
//        startTime = timer.getTime();
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void update(){
        System.out.println("HATCH STATE " + state.name());
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
