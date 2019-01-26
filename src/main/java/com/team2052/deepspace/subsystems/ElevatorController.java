package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;

public class ElevatorController {

    private TalonSRX elevatorMotor = new TalonSRX(Constants.Controls.kElevatorMotorId);

    //singleton
    private static ElevatorController instance = null;
    public static ElevatorController getInstance() {
        if (instance == null) {
            try {
                instance = new ElevatorController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Elevator: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private double goalElevatorInches; //double to keep track of the goal elevator height

    private boolean runningInOpenLoop = false;//boolean to keep track whether using encoders or emergency adjustment

    //constructor
    private ElevatorController(){
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
        elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    }

    public void zeroSensor(){//zeros sensor at beginning of the match
        elevatorMotor.setSelectedSensorPosition(0, 0, Constants.DriveTrain.kCANBusConfigTimeoutMS);
    }

    public void setCurrentPosAsTarget(){//used to stop jerking
        setAndVerifyGoalInches((int)getHeightInches());
    }

    public double getHeightInches() {//used for getting the height of the elevator
        int encoderPos = elevatorMotor.getSelectedSensorPosition(0);
        double revolutions = encoderPos / Constants.Elevator.kElevatorTicksPerRot;
        double inches = revolutions * Constants.Elevator.kElevatorInchesPerRotation;
        return inches;
    }

    public void setTarget(ElevatorPresets posEnum) {//sets the target and puts it through setAndVerifyGoalInches to check it
        runningInOpenLoop = false;
        double calcTarget = getElevatorPresetsHeights(posEnum);//sets goal to the correct inches according to the preset
        setAndVerifyGoalInches(calcTarget);//calls setAndVerifyGoalInches to set and check the goal inches
    }

    //makes sure that the elevator doesn't go above the max height or below 0 in.  Runs the motors
    private void setAndVerifyGoalInches(double newGoalInches){
        if (newGoalInches >  Constants.Elevator.kElevatorMaxHeight) {
            goalElevatorInches = Constants.Elevator.kElevatorMaxHeight;
        } else if (newGoalInches < Constants.Elevator.kElevatorMinHeight) {
            System.out.println("INVALID ELEVATOR VALUE : " + newGoalInches);
            goalElevatorInches = Constants.Elevator.kElevatorMinHeight;
        } else {
            goalElevatorInches = newGoalInches;
        }
        if (goalElevatorInches <= getHeightInches()) {
            elevatorMotor.configMotionCruiseVelocity((int)(5550 * .5), 10);
            elevatorMotor.configMotionAcceleration((int)(5550 * .5 * 2), 10);
        } else{
            elevatorMotor.configMotionCruiseVelocity((int)(5550 * .9), 10);
            elevatorMotor.configMotionAcceleration((int)(5550 * .9 * 2), 10);
        }
    }

    public boolean getCarriageIsMoving (){//used to get whether the carriage is moving or not
        boolean velocity = elevatorMotor.getSelectedSensorVelocity(0) != 0;
        return velocity;
    }

    private boolean lastPressedUpState = false;//keeps track of the whether the button was pressed during the last cycle
    public void setElevatorAdjustmentUp(boolean isPressed){
        if(isPressed && runningInOpenLoop){//if the button for up adjustment is pressed get out of open loop
            setCurrentPosAsTarget();
            runningInOpenLoop = false;
        }

        //if the booleans state changed from the last cycle, makes it so the button has to be pressed or released for the height to change(not up 1 every cycle)
        if(isPressed != lastPressedUpState){
            setAndVerifyGoalInches(goalElevatorInches + 1);//adds one inch to the height
        }
        lastPressedUpState = isPressed;//saves what the button is for the next cycle
    }

    //same as up but for down
    private boolean lastPressedDownState = false;
    public void setElevatorAdjustmentDown(boolean isPressed){
        if(isPressed && runningInOpenLoop){
            setCurrentPosAsTarget();
            runningInOpenLoop = false;
        }
        if(isPressed !=lastPressedDownState){
            setAndVerifyGoalInches(goalElevatorInches - 1);
        }
        lastPressedDownState = isPressed;
    }

    private boolean emergencyUpWasPressed = false; //able to stop motor only once after pressed
    public void setEmergencyUp (boolean isPressed) {
        if (isPressed) {
            runningInOpenLoop = true;//switching to open loop
            elevatorMotor.set(ControlMode.PercentOutput,Constants.Elevator.kElevatorEmergencyUpPower);
            emergencyUpWasPressed = true;
        } else {
            if (emergencyUpWasPressed) {
                elevatorMotor.set(ControlMode.PercentOutput, Constants.Elevator.kElevatorEmergencyHoldPower);
                emergencyUpWasPressed = false; //Exactly the same as EmergencyDown, except for up.
            }
        }
    }

    private boolean emergencyDownWasPressed = false; //able to stop motor only once after pressed
    public void setEmergencyDown (boolean isPressed) {
        if (isPressed) {
            runningInOpenLoop = true;//switching to open loop
            elevatorMotor.set(ControlMode.PercentOutput,0.0);
            emergencyDownWasPressed = true;
        } else {
            if (emergencyDownWasPressed) {
                elevatorMotor.set(ControlMode.PercentOutput, Constants.Elevator.kElevatorEmergencyHoldPower);
                emergencyDownWasPressed = false; //Exactly the same as EmergencyDown, except for up.
            }
        }
    }

    public double getElevatorPresetsHeights(ElevatorPresets PosEnum){
        switch (PosEnum){
            case GROUND_CARGO:
                return Constants.Elevator.kGroundCargoHeight;
            case HATCH_LEVEL1:
                return Constants.Elevator.kHatchLevel1Height;
            case HATCH_LEVEL2:
                return Constants.Elevator.kHatchLevel2Height;
            case HATCH_LEVEL3:
                return Constants.Elevator.kHatchLevel3Height;
            case CARGOSHIP_CARGO:
                return Constants.Elevator.kCargoShipCargoHeight;
            case ROCKET_CARGO1:
                return Constants.Elevator.kRocketCargoHeight1;
            case ROCKET_CARGO2:
                return Constants.Elevator.kRocketCargoHeight2;
            case ROCKET_CARGO3:
                return Constants.Elevator.kRocketCargoHeight3;
        }
    return 0.0;
    }
    public enum ElevatorPresets {
        GROUND_CARGO,
        HATCH_LEVEL1,
        HATCH_LEVEL2,
        HATCH_LEVEL3,
        CARGOSHIP_CARGO,
        ROCKET_CARGO1,
        ROCKET_CARGO2,
        ROCKET_CARGO3,

    }
}
