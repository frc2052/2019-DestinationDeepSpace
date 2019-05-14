package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class LifterController {
    private TalonSRX rampMotor;


    private static LifterController instance = null;
    public static LifterController getInstance() {
        if (instance == null) {
            try {
                instance = new LifterController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create LifterController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private LifterController() {
        rampMotor = new TalonSRX(Constants.Lifter.kRampMotor);
        rampMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0,10);

        /* set the peak and nominal outputs */
        rampMotor.configNominalOutputForward(0, 10);
        rampMotor.configNominalOutputReverse(0, 10);
        rampMotor.configPeakOutputForward(0.4, 10);
        rampMotor.configPeakOutputReverse(-0.2, 10);

        /* set closed loop gains in slot0 - see documentation */
        rampMotor.selectProfileSlot(0, 0);
        rampMotor.config_kF(0, 0.2, 10); //1843
        rampMotor.config_kP(0, .8, 10);
        rampMotor.config_kI(0, 0, 10);
        rampMotor.config_kD(0, 0, 10);
    }


    public void printLifterEncoder(){
        System.out.println("lifter pos: " + rampMotor.getSelectedSensorPosition());
    }


    private Solenoid liftOutSolenoid = new Solenoid(Constants.Lifter.kLifterOutSolenoidId);
    private int lifterButtonPressCount = 0;
    private boolean wasLastPressed = false;

    public void setLegsDown(boolean isPressed) {


        if (isPressed && !wasLastPressed){ //button state has changed, was up and is now down
            lifterButtonPressCount++; //keep track of how many times the button was pressed
        }

        //keep track of whether button is up or down
        wasLastPressed = isPressed;

        if ((Timer.getMatchTime() < 30 && lifterButtonPressCount > 0) || lifterButtonPressCount > 10) {
            System.out.println("Lifter setting to " + isPressed);
            liftOutSolenoid.set(isPressed);
        }
    }

    private final int KDownEncoderPosition = -85000;

    public void resetEncoder () {
        rampMotor.set(ControlMode.Position, 0);
    }

    public void setRampDown(boolean isPressed) {
        if(isPressed) {
            rampMotor.set(ControlMode.PercentOutput, -.2);
        }else{
            rampMotor.set(ControlMode.PercentOutput, 0);
        }
    }
}
