package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import com.team2052.lib.ILoopable;
import edu.wpi.first.wpilibj.Solenoid;

public class GroundIntakeController implements ILoopable {
    /* True is Intake, false is Outtake.
    Could be enum, but because intake and outtake already are enums with a done case
    There's no reason complicate it. */
    private static GroundIntakeController instance = new GroundIntakeController();
    public static GroundIntakeController getInstance(){
        return instance;
    }//Singleton

    private IntakeState controllerState; //Non-Static Context

    private final TalonSRX groundIntakeMotor = new TalonSRX(Constants.Intake.kGroundIntakeMotor);
    public final Solenoid Grabber2 = new Solenoid(Constants.Intake.kGrabber2SolenoidId);
    public final Solenoid Grabber1 = new Solenoid(Constants.Intake.kGrabber1SolenoidId);
    private boolean lastPressedState = false;
    public void pickupFromFloor(boolean isPressed) {
        if (isPressed) {
            controllerState = IntakeState.DOWN_OPEN;
        } else if (!isPressed && lastPressedState) { //just let go of button
            controllerState = IntakeState.STARTING;
        }
    }

    public void setUpClosed(boolean isPressed){
        if(isPressed){
            controllerState = IntakeState.UP_CLOSED;
        }
    }

    public void placement(boolean isPressed){
        controllerState = IntakeState.PLACEMENT;
    }


    @Override
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
                case LOWERING:
                    setLiftGroundIntakeState(false);
                    System.out.println("LOWERING");
                    if(timer.hasPassedTime(Constants.Intake.kReleaseTime)) { //Time before release
                        setOuttakeState(groundOuttakeState.RELEASING);
                    }
                case RELEASING:
                    setGrabGroundIntakeState(false);
                    System.out.println("RELEASING");
                    if(timer.hasPassedTime(Constants.Intake.kEscapeTime)) { //Time before done
                        setOuttakeState(groundOuttakeState.DONE);
                    }
                case DONE:
                    timer.stop();
                    timer.reset();//doesn't matter if you keep telling it to stop/reset as long as you start it without these applying
            }
        }
    } //Continuously updates, when state is set to grabbing, it goes through the cases over Constants.Intake.kLiftTime, then restarts the timer.

    public void onStart(){ }//Requirement of Loopable
    public void onStop(){ }//Requirement of Loopable

    private enum IntakeState {
        DOWN_OPEN,
        DOWN_CLOSED,
        UP_CLOSED,
        STARTING,
        PLACEMENT
    }

}
