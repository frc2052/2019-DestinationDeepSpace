package com.team2052.deepspace;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {

    private static Controls instance = new Controls();

    private Controls() {
    }

    public static Controls getInstance() {
        return instance;
    }

    private Joystick turnPrimaryStick = new Joystick(0); //left joystick
    private Joystick tankPrimaryStick = new Joystick(1);//right joystick
    private Joystick secondaryControlPanel = new Joystick(2);

    public double getDriveTank() {
        double val = -tankPrimaryStick.getY();
        if (val < .15 && val > -.15) {
            val = 0;
        }
        return val;
    } //these return the values for the joysticks for other classes

    public double getUnusedTurn() {
        return tankPrimaryStick.getX();
    }

    public double getUnusedTank() {
        return tankPrimaryStick.getY();
    }

    public double getDriveTurn() {
        double val = turnPrimaryStick.getX();
        if (val < .15 && val > -.15) {
            val = 0;
        }
        return val;
    }


    public boolean getQuickTurn(){
        return turnPrimaryStick.getRawButton(Constants.Controls.kQuickTurnButton);
    }

    public boolean getCameraToggle(){return turnPrimaryStick.getRawButton(5);}
    public boolean getOuttake() {return tankPrimaryStick.getTrigger();}
    public boolean legClimber() {
        return tankPrimaryStick.getRawButton(Constants.Controls.kLegClimberButton);
    }
    public boolean lowerClimber(){
           return tankPrimaryStick.getRawButton(Constants.Controls.kLegClimberLowerButton);
    }

    public boolean getIntake () {return secondaryControlPanel.getTrigger();}
    public boolean getGrab(){return secondaryControlPanel.getRawButton(Constants.Controls.kGrabButton);}
    public boolean getGroundIntake () {return secondaryControlPanel.getRawButton(Constants.Controls.kGroundIntakeButton); }
    public boolean getGroundOuttake () {return secondaryControlPanel.getRawButton(Constants.Controls.kGroundOuttakeButton); }


    public boolean getShift(){return tankPrimaryStick.getRawButton(Constants.Controls.kShiftButton);}

    public boolean getLightFollow(){return tankPrimaryStick.getRawButton(Constants.Controls.kLightSensorFollowButton);}

    //////elevator//////
    public boolean getElevatorGroundCargo(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorGroundGargoButton);}
    public boolean getElevatorHatch1(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorHatch1Button);}
    public boolean getElevatorHatch2(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorHatch2Button);}
    public boolean getElevatorHatch3(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorHatch3Button);}
    public boolean getElevatorCargoShipCargo(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorCargoShipCargoButton);}
    public boolean getElevatorRocketCargo1(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorCargo1Button);}
    public boolean getElevatorRocketCargo2(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorCargo2Button);}
    public boolean getElevatorRocketCargo3(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorCargo3Button);}

    public boolean getElevatorAdjustmentUp(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorAdjustmentUpButton);}
    public boolean getElevatorAdjustmentDown(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorAdjustmentDownButton);}

    public boolean getElevatorEmergencyUp(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorEmergencyUpButton);}
    public boolean getElevatorEmergencyDown(){return secondaryControlPanel.getRawButton(Constants.Controls.kElevatorEmergencyDownButton);}

    public boolean trigger() {
        return turnPrimaryStick.getTrigger();
    }

    public boolean autoOverride(){
        return tankPrimaryStick.getRawButton(Constants.Controls.kautoOverrideButton);
    }

    }