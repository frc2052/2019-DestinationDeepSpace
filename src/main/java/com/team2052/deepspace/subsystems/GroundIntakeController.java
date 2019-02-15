package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import com.team2052.lib.ILoopable;

public class GroundIntakeController implements ILoopable {
    /* True is Intake, false is Outtake.
    Could be enum, but because intake and outtake already are enums with a done case
    There's no reason complicate it. */
    private static GroundIntakeController instance = null;
    public static GroundIntakeController getInstance() {
        if (instance == null) {
            try {
                instance = new GroundIntakeController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create GroundIntakeController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private GroundIntakeController() {
        grabber = HatchGrabberController.getInstance();
        groundIntakeMotor = new TalonSRX(Constants.Intake.kGroundIntakeMotor);
        groundIntakeMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,10);
        groundIntakeMotor.setInverted(true);
        groundIntakeMotor.setSensorPhase(true);

        /* set the peak and nominal outputs */
        groundIntakeMotor.configNominalOutputForward(0, 10);
        groundIntakeMotor.configNominalOutputReverse(0, 10);
        groundIntakeMotor.configPeakOutputForward(0.1, 10);
        groundIntakeMotor.configPeakOutputReverse(0.1, 10);

        /* set closed loop gains in slot0 - see documentation */
        groundIntakeMotor.selectProfileSlot(0, 0);
        groundIntakeMotor.config_kF(0, 0.2, 10); //1843
        groundIntakeMotor.config_kP(0, .8, 10);
        groundIntakeMotor.config_kI(0, 0, 10);
        groundIntakeMotor.config_kD(0, 0, 10);

        /* zero the sensor */
        groundIntakeMotor.setSelectedSensorPosition(0, 0, 10);
    }

    private HatchGrabberController grabber = null;
    private IntakeState controllerState = IntakeState.STARTING;
    private IntakeState wantControllerState = IntakeState.STARTING;
    private TalonSRX groundIntakeMotor = null;
    private final int KStartingEncoderPosition = 0;
    private final int KUpEncoderPosition = -20000;
    private final int KPlacementEncoderPosition = -90000;
    private final int KDownEncoderPosition = -152000;
    private boolean lastPressedState = false;

//    public void pickupFromFloor(boolean isPressed) {
//        //System.out.println("Ground intake " + groundIntakeMotor.getSelectedSensorPosition());
//        if (isPressed) {
//            wantControllerState = IntakeState.DOWN_OPEN;
//        } else if (!isPressed && lastPressedState) { //just let go of button
//            wantControllerState = IntakeState.STARTING;
//        }
//        lastPressedState = isPressed;
//    }
//
//    public void setUpClosed(boolean isPressed){
//        if(isPressed){
//            wantControllerState = IntakeState.UP_CLOSED;
//        }
//    }
//
//    public void placement(boolean isPressed){
//        if(isPressed){
//            wantControllerState = IntakeState.PLACEMENT;
//        }
//    }

    public void setWantState(IntakeState want) {
        wantControllerState = want;
    }


    @Override
    public void update(){
            switch (controllerState) {
                case DOWN_OPEN:
                    groundIntakeMotor.set(ControlMode.Position, KDownEncoderPosition );
                    if(wantControllerState == IntakeState.STARTING || wantControllerState == IntakeState.DOWN_CLOSED){
                        grabberOpen(false);
                        controllerState = IntakeState.DOWN_CLOSED;
                    }
                    break;
                case DOWN_CLOSED:
                    groundIntakeMotor.set(ControlMode.Position, KDownEncoderPosition );
                    if(wantControllerState == IntakeState.STARTING){
                        controllerState = IntakeState.STARTING;
                    } else if (wantControllerState == IntakeState.DOWN_OPEN){
                        //Don't open the grabber until you are 90% to the bottom
                       if (groundIntakeMotor.getSelectedSensorPosition() >(KDownEncoderPosition * 0.9)){
                           grabberOpen(true);
                           controllerState = IntakeState.DOWN_OPEN;
                       }
                    }
                    break;
                case UP_CLOSED:
                    groundIntakeMotor.set(ControlMode.Position,KUpEncoderPosition);
                    if (wantControllerState == IntakeState.DOWN_OPEN || wantControllerState == IntakeState.DOWN_CLOSED){
                        controllerState = IntakeState.DOWN_CLOSED;
                    }
                    else if (wantControllerState == IntakeState.PLACEMENT){
                        controllerState = IntakeState.PLACEMENT;
                    }
                    else if (wantControllerState == IntakeState.STARTING){
                        grabberOpen(false);
                        controllerState = IntakeState.STARTING;
                    }
                    break;
                case PLACEMENT:
                    groundIntakeMotor.set(ControlMode.Position,KPlacementEncoderPosition);
                    if (groundIntakeMotor.getSelectedSensorPosition() > KUpEncoderPosition){
                        grabberOpen(true);
                    }
                    if (wantControllerState == IntakeState.STARTING){
                        grabberOpen(false);
                        controllerState = IntakeState.STARTING;
                    } else if (wantControllerState == IntakeState.DOWN_OPEN ||wantControllerState == IntakeState.DOWN_CLOSED){
                        controllerState = IntakeState.DOWN_OPEN;
                    }else if (wantControllerState == IntakeState.UP_CLOSED){
                        grabberOpen(false);
                        controllerState = IntakeState.UP_CLOSED;
                    }
                    break;
                default: //starting position
                    groundIntakeMotor.set(ControlMode.Position, KStartingEncoderPosition);
                    System.out.println("Ground State STARTING");
                    if (wantControllerState == IntakeState.UP_CLOSED || wantControllerState == IntakeState.PLACEMENT){
                        grabberOpen(false);
                        controllerState = IntakeState.UP_CLOSED;
                    }else if (wantControllerState == IntakeState.DOWN_CLOSED || wantControllerState == IntakeState.DOWN_OPEN){
                        grabberOpen(false);
                        controllerState = IntakeState.DOWN_CLOSED;
                    }

        }
    }


    private void grabberOpen(boolean isOpen) {
       grabber.setHatchPlace(isOpen);
    }

    public void setStartPos() {
        wantControllerState = IntakeState.STARTING;
    }
    public void onStart(){ }//Requirement of Loopable
    public void onStop(){ }//Requirement of Loopable
    public boolean getPlacementComplete() {
        return controllerState == IntakeState.PLACEMENT && grabber.getIsOpen();
    }
    public boolean getIsPlacing() {
        return controllerState == IntakeState.PLACEMENT;
    }

    public enum IntakeState {
        DOWN_OPEN,
        DOWN_CLOSED,
        UP_CLOSED,
        STARTING,
        PLACEMENT
    }
}
