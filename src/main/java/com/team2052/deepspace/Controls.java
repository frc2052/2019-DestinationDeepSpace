package com.team2052.deepspace;

import com.team2052.deepspace.subsystems.IntakeController;
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
        double val = -rightPrimaryStick.getY();
        if (val < .15 && val > -.15) {
            val = 0;
        }
        return val;
    } //these return the values for the joysticks for other classes

    public double getTurnJoy1() {
        return leftPrimaryStick.getX();
    }

    public double getTankJoy2() {
        return leftPrimaryStick.getY();
    }

    public double getTurnJoy2() {
        double val = leftPrimaryStick.getX();
        if (val < .15 && val > -.15) {
            val = 0;
        }
        return val;
    }

    public boolean legClimber() {
        return rightPrimaryStick.getRawButton(Constants.LegClimber.kLegClimberButton);
    }
    public boolean lowerClimber(){return rightPrimaryStick.getRawButton(Constants.LegClimber.kLegClimberLowerButton);}


    public boolean reset() {
        return rightPrimaryStick.getTrigger();
    }
    public boolean getQuickTurn(){
        return leftPrimaryStick.getRawButton(Constants.Controls.kQuickTurnButton);
    }

    public boolean getOuttake() {return secondaryStick.getRawButton(1);}
    public boolean getIntake () {return secondaryStick.getTrigger();}
    public boolean getGrab(){return secondaryStick.getRawButton(Constants.Intake.kGrabButton);}
    public boolean getGroundIntake () {return secondaryStick.getRawButton(Constants.Intake.kGroundIntakeButton); }
    public boolean getGroundOuttake () {return secondaryStick.getRawButton(Constants.Intake.kGroundOuttakeButton); }

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
        return rightPrimaryStick.getTrigger();
    }

    public boolean autoOverride(){
        return leftPrimaryStick.getRawButton(Constants.Controls.kautoOverrideButton);
    }

    public boolean getLineFollow(){return rightPrimaryStick.getRawButton(Constants.LineFollower.kLightSensorFollowButton);}
}

