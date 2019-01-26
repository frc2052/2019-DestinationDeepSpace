package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.KnightTimer;
import com.team2052.deepspace.Loopable;
import edu.wpi.first.wpilibj.Solenoid;

public class GroundIntake implements Loopable {
    public void groundIntake() { //Tell the system to intake
        setIntakeState(groundIntakeState.GRABBING);
        timer.start();
    }
    private groundIntakeState intakeState; //Non-Static Context
    private static GroundIntake instance = new GroundIntake();
    public static GroundIntake getInstance(){
        return instance;
    }//Singleton
    private KnightTimer timer = new KnightTimer();
    public final Solenoid Lifter = new Solenoid(Constants.Intake.Tape);
    public final Solenoid Grabber = new Solenoid(Constants.Intake.Tap3);
    private boolean liftGroundIntakeState; //Getter and Setter
    public boolean getLiftGroundIntakeState(){ return liftGroundIntakeState; }
    public void setLiftGroundIntakeState(boolean state){
        liftGroundIntakeState = state;
        Lifter.set(state);
    }
    private boolean grabGroundIntakeState; //Getter and Setter
    public boolean getGrabGroundIntakeState(){ return grabGroundIntakeState; }
    public void setGrabGroundIntakeState(boolean state){
        grabGroundIntakeState = state;
        Grabber.set(state);
    }
    private void setIntakeState (groundIntakeState state){
        intakeState = state;
    }
    public boolean groundIntakeState(){
        if ((intakeState == groundIntakeState.DONE)) return true;
        else return false;
    }
    public void update(){
        switch(intakeState){
            case GRABBING:
                setGrabGroundIntakeState(true);
                if(timer.hasPassedTime(Constants.Intake.kGrabTime)) {
                    setIntakeState(groundIntakeState.LIFTING);
                }
            case LIFTING:
                setLiftGroundIntakeState(true);
                if(timer.hasPassedTime(Constants.Intake.kLiftTime)) {
                    setIntakeState(groundIntakeState.DONE);
                }
            case DONE:
                timer.stop();
                timer.reset();//doesn't matter if you keep telling it to stop/reset as long as you start it without these applying
        }
    } //Continuously updates, when state is set to grabbing, it goes through the cases over Constants.Intake.kLiftTime, then restarts the timer.

    public void onStart(){ }//Requirement of Loopable
    public void onStop(){ }//Requirement of Loopable


    private enum groundIntakeState {
        GRABBING,
        LIFTING,
        DONE
    }

}
