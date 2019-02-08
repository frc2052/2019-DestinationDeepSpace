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
    private Joystick secondaryStick = new Joystick(2);

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

    public boolean legClimber() {
        return tankPrimaryStick.getRawButton(Constants.Controls.kLegClimberButton);
    }
    public boolean lowerClimber(){
           return tankPrimaryStick.getRawButton(Constants.Controls.kLegClimberLowerButton);
    }


    public boolean getQuickTurn(){
        return turnPrimaryStick.getRawButton(Constants.Controls.kQuickTurnButton);
    }

    public boolean getOuttake() {return secondaryStick.getTrigger();}
    public boolean getIntake () {return secondaryStick.getTrigger();}
    public boolean getGrab(){return secondaryStick.getRawButton(Constants.Controls.kGrabButton);}
    public boolean getGroundIntake () {return secondaryStick.getRawButton(Constants.Controls.kGroundIntakeButton); }
    public boolean getGroundOuttake () {return secondaryStick.getRawButton(Constants.Controls.kGroundOuttakeButton); }

    public boolean getShift(){return tankPrimaryStick.getRawButton(2);}

    public boolean getVisionTrack(){
        return secondaryStick.getRawButton(Constants.Controls.kVisionTrackButton);
    };

    //////elevator//////
    public boolean getElevatorGroundCargo(){return secondaryStick.getRawButton(Constants.Controls.kElevatorGroundGargoButton);}
    public boolean getElevatorHatch1(){return secondaryStick.getRawButton(Constants.Controls.kElevatorHatch1Button);}
    public boolean getElevatorHatch2(){return secondaryStick.getRawButton(Constants.Controls.kElevatorHatch2Button);}
    public boolean getElevatorHatch3(){return secondaryStick.getRawButton(Constants.Controls.kElevatorHatch3Button);}
    public boolean getElevatorCargoShipCargo(){return secondaryStick.getRawButton(Constants.Controls.kElevatorCargoShipCargoButton);}
    public boolean getElevatorRocketCargo1(){return secondaryStick.getRawButton(Constants.Controls.kElevatorCargo1Button);}
    public boolean getElevatorRocketCargo2(){return secondaryStick.getRawButton(Constants.Controls.kElevatorCargo2Button);}
    public boolean getElevatorRocketCargo3(){return secondaryStick.getRawButton(Constants.Controls.kElevatorCargo3Button);}

    public boolean getElevatorAdjustmentUp(){return secondaryStick.getRawButton(Constants.Controls.kElevatorAdjustmenUpButton);}
    public boolean getElevatorAdjustmentDown(){return secondaryStick.getRawButton(Constants.Controls.kElevatorAdjustmenDownButton);}

    public boolean getElevatorEmergencyUp(){return secondaryStick.getRawButton(Constants.Controls.kElevatorEmergencyUpButton);}
    public boolean getElevatorEmergencyDown(){return secondaryStick.getRawButton(Constants.Controls.kElevatorEmergencyDownButton);}

    public boolean trigger() {
        return turnPrimaryStick.getTrigger();
    }

    public boolean autoOverride(){
        return tankPrimaryStick.getRawButton(Constants.Controls.kautoOverrideButton);
    }

    public boolean getLightFollow(){return tankPrimaryStick.getRawButton(Constants.LineFollower.kLightSensorFollowButton);}
}