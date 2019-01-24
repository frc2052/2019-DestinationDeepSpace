package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class GroundIntake {
    private double intakeDelay;
    private static GroundIntake instance = new GroundIntake();
    public static GroundIntake getInstance(){
        return instance;
    }
    public final Solenoid Lifter = new Solenoid(Constants.Intake.Tape);
    public final Solenoid Grabber = new Solenoid(Constants.Intake.Tap3);
    private boolean groundIntakeState; //Getter and Setter
    public boolean getGroundIntakeState(){ return groundIntakeState; }
    public void setGroundIntakeState(boolean state){
        groundIntakeState = state;
        Lifter.set(state);
    }
    private boolean grabGroundIntakeState; //Getter and Setter
    public boolean getGrabGroundIntakeState(){ return grabGroundIntakeState; }
    public void setGrabGroundIntakeState(boolean state){
        grabGroundIntakeState = state;
        Grabber.set(state);
    }
    public void groundIntake(){
        setGroundIntakeState(true);

    }
    public enum groundIntakeState {
        GRABBING,
        LIFTING,
        DONE
    }

}
