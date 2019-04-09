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
    //private DigitalOutput limitSwitch = null;

    private LegClimberController(){
        legClimberMotor = new TalonSRX(Constants.LegClimber.kLegClimberTalon1id);
        //limitSwitch = new DigitalOutput(4);
        legClimberMotor.configFactoryDefault();
        legClimberMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        legClimberMotor.setNeutralMode(NeutralMode.Brake);
        legClimberMotor.setInverted(false);
        legClimberMotor.setSensorPhase(true);
    }

    public void resetEncoders() {
        legClimberMotor.setSelectedSensorPosition(0, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        legClimberButtonPressCount = 0;
        startTime = Timer.getFPGATimestamp();
    }

    private int legClimberButtonPressCount = 0;
    private boolean wasLastPressed = false;
    private double startTime = 0;

    public void runClimber(State state) {
        /*
        if (limitSwitch.get() && state != State.OVERRIDEDOWN && state != State.OVERRIDEUP && state != State.DOWN){
            state = State.STOP;
            System.out.println();
        }*/
        switch (state){
            case UP:

                double timeLeft = Timer.getMatchTime(); //this SHOULD be the time since the first "mode" began (aka, Auto)
                if (!wasLastPressed){ //button state has changed, was up and is now down
                    legClimberButtonPressCount++; //keep track of how many times the button was pressed
                }

                //keep track of whether button is up or down
                wasLastPressed = true;

                //System.out.println("time: " + timePassed );
                if (timeLeft < 30 || legClimberButtonPressCount > 0) {//30 seconds left in the match OR button has been pressed 10 times
                    printEncoder();
                    if(legClimberMotor.getSelectedSensorPosition() <= Constants.LegClimber.kClimberMotorDistance){
                        legClimberMotor.set(ControlMode.PercentOutput, 1); //only drive forward if we haven't reached maximum encoder value
                        System.out.println("RUNNING" + " FPGA: " + Timer.getFPGATimestamp() + " MATCH TIME: " + Timer.getMatchTime());
                        if(legClimberMotor.getSelectedSensorPosition() == 0){
                            System.out.println("ERROR: WARNING CLIMBER ENCODER IS 0");
                        }
                    }
                        //with Assistance
                        //LegClimberSolenoid1.set(true);
                }
                else
                {
                    System.out.println("Not enough time has passed to put leg down, and not more than 10 presses (" + legClimberButtonPressCount + ")");
                }
                break;
            case DOWN:
                wasLastPressed = false;
                printEncoder();
                if (legClimberMotor.getSelectedSensorPosition() > 0){
                    legClimberMotor.set(ControlMode.PercentOutput, -.5);
                    System.out.println("RUNNING BACK");
                }
                break;
            case OVERRIDEUP:
                System.out.println("OVERRIDING DOWN");
                legClimberMotor.set(ControlMode.PercentOutput, 1); //only drive forward if we haven't reached maximum encoder value
                break;
            case OVERRIDEDOWN:
                System.out.println("OVERRIDING UP");
                legClimberMotor.set(ControlMode.PercentOutput, -.5);
                break;
            case STOP:
                //no break, fall through to default
            default:
                wasLastPressed = false;
                stopClimber();
                break;
        }

    }


    public void stopClimber(){
        legClimberMotor.set(ControlMode.PercentOutput, 0.0);
    }
    public void printEncoder(){
        System.out.println("climber pos: " + legClimberMotor.getSelectedSensorPosition());

    }

    public enum State{
        UP,
        DOWN,
        OVERRIDEUP,
        OVERRIDEDOWN,
        STOP
    }

}
