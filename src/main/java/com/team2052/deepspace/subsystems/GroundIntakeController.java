package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;

public class GroundIntakeController {
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
//        groundIntakeMotor.setInverted(true);
//        groundIntakeMotor.setSensorPhase(true);

        /* set the peak and nominal outputs */
        groundIntakeMotor.configNominalOutputForward(0, 10);
        groundIntakeMotor.configNominalOutputReverse(0, 10);
        groundIntakeMotor.configPeakOutputForward(0.4, 10);
        groundIntakeMotor.configPeakOutputReverse(-0.2, 10);

        /* set closed loop gains in slot0 - see documentation */
        groundIntakeMotor.selectProfileSlot(0, 0);
        groundIntakeMotor.config_kF(0, 0.2, 10); //1843
        groundIntakeMotor.config_kP(0, .8, 10);
        groundIntakeMotor.config_kI(0, 0, 10);
        groundIntakeMotor.config_kD(0, 0, 10);

        resetEncoder();
    }

    public void resetEncoder() {
        /* zero the sensor */
        groundIntakeMotor.setSelectedSensorPosition(0, 0, 10);
        this.wantControllerState = IntakeState.STARTING;
        this.controllerState = IntakeState.STARTING;
    }

    private HatchGrabberController grabber = null;
    private IntakeState controllerState = IntakeState.STARTING;
    private IntakeState wantControllerState = IntakeState.STARTING;
    private TalonSRX groundIntakeMotor = null;
    private final int KStartingEncoderPosition = 0;
    private final int KUpEncoderPosition = -35000;
    private final int KPlacementEncoderPosition = -80000;
    private final int KDownEncoderPosition = -125000;
    private boolean lastPressedState = false;

    public void setWantState(IntakeState wantControllerState) {
        controllerState = wantControllerState;
        switch (controllerState) {
            case DOWN:
                //System.out.println("Ground State DOWNOPEN");
                groundIntakeMotor.set(ControlMode.Position, KDownEncoderPosition);
                break;
            case READY:
                //System.out.println("Ground State UP");
                groundIntakeMotor.set(ControlMode.Position, KUpEncoderPosition);
                break;
            case PLACEMENT:
                //System.out.println("Ground State PLACING");
                groundIntakeMotor.set(ControlMode.Position, KPlacementEncoderPosition);
                break;
            default: //starting position
                groundIntakeMotor.set(ControlMode.Position, KStartingEncoderPosition);
                //System.out.println("Ground State STARTING");
        }
    }

    public void setGrabberOpen (boolean isOpen) {
        grabber.setHatchPlace(isOpen);
    }

    public boolean getPlacementComplete() {
        boolean downFarEnough = groundIntakeMotor.getSelectedSensorPosition() < KPlacementEncoderPosition * .9;
        return controllerState == IntakeState.PLACEMENT && grabber.getIsOpen() && downFarEnough;
    }

    public enum IntakeState {
        DOWN,
        READY,
        STARTING,
        PLACEMENT
    }
}
