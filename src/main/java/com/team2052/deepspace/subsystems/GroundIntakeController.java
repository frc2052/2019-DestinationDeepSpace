package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
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
    private IntakeState wantControllerState;
    private final TalonSRX groundIntakeMotor = new TalonSRX(Constants.Intake.kGroundIntakeMotor);
    private final Solenoid grabberIn = new Solenoid(Constants.Intake.kGrabber2SolenoidId);
    private final Solenoid grabberOut = new Solenoid(Constants.Intake.kGrabber1SolenoidId);
    private final int KDownEncoderPosition = 100;
    private final int KPlacementEncoderPosition = 45;
    private final int KStartingEncoderPosition = 0;
    private final int KUpEncoderPosition = 10;
    private boolean lastPressedState = false;

    public void pickupFromFloor(boolean isPressed) {
        if (isPressed) {
            wantControllerState = IntakeState.DOWN_OPEN;
        } else if (!isPressed && lastPressedState) { //just let go of button
            wantControllerState = IntakeState.STARTING;
        }
        lastPressedState = isPressed;
    }

    public void setUpClosed(boolean isPressed){
        if(isPressed){
            wantControllerState = IntakeState.UP_CLOSED;
        }
    }

    public void placement(boolean isPressed){
        if(isPressed){
            wantControllerState = IntakeState.PLACEMENT;
        }
    }



    @Override
    public void update(){

            switch (controllerState) {
                case DOWN_OPEN:
                    if(wantControllerState == IntakeState.STARTING || wantControllerState == IntakeState.DOWN_CLOSED){
                        grabberOpen(false);
                        controllerState = IntakeState.DOWN_CLOSED;
                    }
                    break;
                case DOWN_CLOSED:
                    if(wantControllerState == IntakeState.STARTING){
                        groundIntakeMotor.set(ControlMode.Position, KStartingEncoderPosition);
                        controllerState = IntakeState.STARTING;
                    } else if (wantControllerState == IntakeState.DOWN_OPEN){
                        //Don't open the grabber until you are 90% to the bottom
                       if (groundIntakeMotor.getSelectedSensorPosition() >(KDownEncoderPosition * 0.9)){
                           grabberOpen(true);
                           controllerState = IntakeState.DOWN_OPEN;
                       }else {//Keep going down
                           groundIntakeMotor.set(ControlMode.Position, KDownEncoderPosition);
                       }
                    }
                    break;
                case UP_CLOSED:
                    if (wantControllerState == IntakeState.DOWN_OPEN || wantControllerState == IntakeState.DOWN_CLOSED){
                        groundIntakeMotor.set(ControlMode.Position, KDownEncoderPosition );
                        controllerState = IntakeState.DOWN_CLOSED;
                    }
                    else if (wantControllerState == IntakeState.PLACEMENT){
                        groundIntakeMotor.set(ControlMode.Position, KPlacementEncoderPosition);
                        controllerState = IntakeState.PLACEMENT;
                    }
                    else if (wantControllerState == IntakeState.STARTING){
                        grabberOpen(false);
                        groundIntakeMotor.set(ControlMode.Position,KStartingEncoderPosition);
                        controllerState = IntakeState.STARTING;
                    }
                    break;
                case PLACEMENT:
                    if (groundIntakeMotor.getSelectedSensorPosition() >KUpEncoderPosition){
                        grabberOpen(true);
                    }
                    if (wantControllerState == IntakeState.STARTING){
                        grabberOpen(false);
                        groundIntakeMotor.set(ControlMode.Position,KStartingEncoderPosition);
                        controllerState = IntakeState.STARTING;
                    } else if (wantControllerState == IntakeState.DOWN_OPEN ||wantControllerState == IntakeState.DOWN_CLOSED){
                        groundIntakeMotor.set(ControlMode.Position, KDownEncoderPosition);
                        controllerState = IntakeState.DOWN_OPEN;
                    }else if (wantControllerState == IntakeState.UP_CLOSED){
                        grabberOpen(false);
                        groundIntakeMotor.set(ControlMode.Position,KUpEncoderPosition);
                        controllerState = IntakeState.UP_CLOSED;
                    }
                    break;
                default: //starting position
                    if (wantControllerState == IntakeState.UP_CLOSED || wantControllerState == IntakeState.PLACEMENT){
                        grabberOpen(false);
                        groundIntakeMotor.set(ControlMode.Position,KUpEncoderPosition);
                        controllerState = IntakeState.UP_CLOSED;
                    }else if (wantControllerState == IntakeState.DOWN_CLOSED || wantControllerState == IntakeState.DOWN_OPEN){
                        grabberOpen(false);
                        groundIntakeMotor.set(ControlMode.Position,KDownEncoderPosition);
                        controllerState = IntakeState.DOWN_CLOSED;
                    }

        }
    }
    private boolean isGrabberOpen = true;
    private void grabberOpen(boolean isOpen) {
        isGrabberOpen = isOpen;
        grabberIn.set(!isOpen);
        grabberOut.set(isOpen);
    }

    public void setStartPos() {
        wantControllerState = IntakeState.STARTING;
    }
    public void onStart(){ }//Requirement of Loopable
    public void onStop(){ }//Requirement of Loopable
    public boolean getPlacementComplete() {
        return controllerState == IntakeState.PLACEMENT && isGrabberOpen;
    }
private enum IntakeState {
    DOWN_OPEN,
    DOWN_CLOSED,
    UP_CLOSED,
    STARTING,
    PLACEMENT
}



}
