package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.KnightTimer;
import com.team2052.deepspace.Loopable;
import edu.wpi.first.wpilibj.Solenoid;

public class GroundIntakeController implements Loopable {
    /* True is Intake, false is Outtake.
    Could be enum, but because intake and outtake already are enums with a done case
    There's no reason complicate it. */
    public void groundIntake(boolean state) {
        if(state){
            groundState = true;
            intakeState = groundIntakeState.GRABBING;
        } else {
            groundState = false;
            outtakeState = groundOuttakeState.RELEASING;
        }
        timer.start();
    }

    private boolean groundState;
    private KnightTimer timer = new KnightTimer();
    private groundOuttakeState outtakeState;
    private groundIntakeState intakeState; //Non-Static Context

    private static GroundIntakeController instance = new GroundIntakeController();
    public static GroundIntakeController getInstance(){
        return instance;
    }//Singleton

    public final Solenoid Lifter = new Solenoid(Constants.Intake.kLifterId);
    public final Solenoid Grabber = new Solenoid(Constants.Intake.kGrabberId);

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
    } //only this class should want to change where it is in the enum
    public boolean groundIntakeState(){
        return ((intakeState == groundIntakeState.DONE));
    } //Simplified Getter, since nothing should want to know if it's anything but done/not done
    public boolean groundOuttakeState(){
        return ((outtakeState == groundOuttakeState.DONE));
    } //Simplified Getter, since nothing should want to know if it's anything but done/not done

    public void update(){
        if(groundState) {
            switch (intakeState) {
                case GRABBING:
                    setGrabGroundIntakeState(true);
                    System.out.println("GRABBING");
                    if (timer.hasPassedTime(Constants.Intake.kGrabTime)) {
                        setIntakeState(groundIntakeState.LIFTING);
                    }
                case LIFTING:
                    setLiftGroundIntakeState(true);
                    System.out.println("LIFTING");
                    if (timer.hasPassedTime(Constants.Intake.kLiftTime)) {
                        setIntakeState(groundIntakeState.DONE);
                    }
                case DONE:
                    timer.stop();
                    timer.reset();//doesn't matter if you keep telling it to stop/reset as long as you start it without these applying
            }
        } else {
            switch(outtakeState){
                case RELEASING:
                    setGrabGroundIntakeState(false);
                    System.out.println("RELEASING");
                    if(timer.hasPassedTime(Constants.Intake.kReleaseTime)) {
                        setIntakeState(groundIntakeState.LIFTING);
                    }
                case LOWERING:
                    setLiftGroundIntakeState(false);
                    System.out.println("LOWERING");
                    if(timer.hasPassedTime(Constants.Intake.kEscapeTime)) {
                        setIntakeState(groundIntakeState.DONE);
                    }
                case DONE:
                    timer.stop();
                    timer.reset();//doesn't matter if you keep telling it to stop/reset as long as you start it without these applying
            }
        }
    } //Continuously updates, when state is set to grabbing, it goes through the cases over Constants.Intake.kLiftTime, then restarts the timer.

    public void onStart(){ }//Requirement of Loopable
    public void onStop(){ }//Requirement of Loopable


    private enum groundIntakeState {
        GRABBING,
        LIFTING,
        DONE
    }
    private enum groundOuttakeState {
        RELEASING,
        LOWERING,
        DONE
    } //Probably not needed, but it's better xplicit what is happening.

}
