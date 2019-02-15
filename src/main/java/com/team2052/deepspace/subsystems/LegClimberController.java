package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Timer;

public class LegClimberController {

    private static LegClimberController instance= null;
    public static LegClimberController getInstance(){
        if (instance == null) {
            try {
                instance = new LegClimberController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create LegClimberController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private TalonSRX legClimberMotor = null;

    private LegClimberController(){
        legClimberMotor = new TalonSRX(Constants.LegClimber.kLegClimberTalon1id);
        legClimberMotor.configFactoryDefault();
        legClimberMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        legClimberMotor.setNeutralMode(NeutralMode.Brake);
        legClimberMotor.setInverted(false);
        legClimberMotor.setSensorPhase(true);
    }

    public void resetEncoders() {
        legClimberMotor.setSelectedSensorPosition(0, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
    }

    private int legClimberButtonPressCount = 0;
    private boolean wasLastPressed = false;

    public void setLegClimber(boolean isPressed) {
        double timePassed = Timer.getFPGATimestamp(); //this SHOULD be the time since the first "mode" began (aka, Auto)
        if (isPressed && !wasLastPressed){ //button state has changed, was up and is now down
            legClimberButtonPressCount++; //keep track of how many times the button was pressed
        }

        //keep track of whether button is up or down
        wasLastPressed = isPressed;

//        System.out.println("time: " + timePassed );
        if (timePassed <= 120 || legClimberButtonPressCount > 10) {//30 seconds left in the match OR button has been pressed 10 times
            if (isPressed) {
                if(legClimberMotor.getSelectedSensorPosition() <= Constants.LegClimber.kClimberMotorDistance){
                    legClimberMotor.set(ControlMode.PercentOutput, 1); //only drive forward if we haven't reached maximum encoder value
                    System.out.println("RUNNING");
                }else {
                    stopClimber();
                }
                //with Assistance
                //LegClimberSolenoid1.set(true);

            }
        }
        else if (isPressed)
        {
            System.out.println("Not enough time has passed to put leg down, and not more than 10 presses (" + legClimberButtonPressCount + ")");
        }
    }
    public void lowerClimber(){
        if (legClimberMotor.getSelectedSensorPosition() > 0){
            legClimberMotor.set(ControlMode.PercentOutput, -1);
            System.out.println("RUNNING BACK");
        } else{
            stopClimber();
        }

    }

    public void stopClimber(){
        legClimberMotor.set(ControlMode.PercentOutput, 0.0);
    }
    public void printEncoder(){
        System.out.println("climber pos: " + legClimberMotor.getSelectedSensorPosition());

    }

}
