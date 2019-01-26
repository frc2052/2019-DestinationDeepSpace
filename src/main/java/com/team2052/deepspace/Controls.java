package com.team2052.deepspace;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {

    private static Controls instance = new Controls();

    private Controls() {
    }

    public static Controls getInstance() {
        return instance;
    }

    private Joystick leftPrimaryStick = new Joystick(0); //left joystick
    private Joystick rightPrimaryStick = new Joystick(1);//right joystick
    private Joystick secondaryStick = new Joystick(2);

    public double getTankJoy1() {
        double val = -leftPrimaryStick.getY();
        if (val < .15 && val > -.15) {
            val = 0;
        }
        return val;
    } //these return the values for the joysticks for other classes

    public double getTurnJoy1() {
        return rightPrimaryStick.getX();
    }

    public double getTankJoy2() {
        return rightPrimaryStick.getY();
    }

    public double getTurnJoy2() {
        double val = rightPrimaryStick.getX();
        if (val < .15 && val > -.15) {
            val = 0;
        }
        return val;
    }

    public boolean legClimber() {
        return leftPrimaryStick.getRawButton(Constants.LegClimber.kLegClimberButton);
    }


    public boolean reset() {
        return leftPrimaryStick.getTrigger();
    }
    public boolean getQuickTurn(){
        return leftPrimaryStick.getRawButton(Constants.Controls.kQuickTurnButton);
    }

    public boolean getOuttake() {return secondaryStick.getRawButton(1);}
    public boolean getIntake () {return secondaryStick.getTrigger();}

  //////elevator//////
    public boolean getElevatorGroundCargo(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorGroundGargoButton);}
    public boolean getElevatorHatch1(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorHatch1Button);}
    public boolean getElevatorHatch2(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorHatch2Button);}
    public boolean getElevatorHatch3(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorHatch3Button);}
    public boolean getElevatorCargoShipCargo(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorCargoShipCargoButton);}
    public boolean getElevatorRocketCargo1(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorCargo1Button);}
    public boolean getElevatorRocketCargo2(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorCargo2Button);}
    public boolean getElevatorRocketCargo3(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorCargo3Button);}

    public boolean getElevatorAdjustmentUp(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorAdjustmenUpButton);}
    public boolean getElevatorAdjustmentDown(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorAdjustmenDownButton);}

    public boolean getElevatorEmergencyUp(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorEmergencyUpButton);}
    public boolean getElevatorEmergencyDown(){return secondaryStick.getRawButton(Constants.Elevator.kElevatorEmergencyDownButton);}

    public boolean trigger() {
        return leftPrimaryStick.getTrigger();
    }
}

