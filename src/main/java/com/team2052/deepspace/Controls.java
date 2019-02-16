package com.team2052.deepspace;

import edu.wpi.first.wpilibj.Joystick;

public class Controls {

    private static Controls instance = new Controls();

    public static Controls getInstance() {
        return instance;
    }

    private Controls() {
    }

    public static final int kTurnJoystickHatchOuttake = 1; //trigger
    public static final int kTurnJoystickAutoOverrideButton = 2;
    public static final int kTurnJoystickQuickTurn = 3;
    public static final int kTurnJoystickCameraToggle = 5;

    public static final int kTankJoystickCargoShoot = 1; // trigger
    public static final int kTankJoystickShiftButton = 2;
    public static final int kTankJoystickVisionDrive = 3;

    public static final int kSecondaryCargoIntake = 1;
    public static final int kSecondaryRocket1 = 2;
    public static final int kSecondaryRocket2 = 3;
    public static final int kSecondaryGroundPickupReady = 6;
    public static final int kSecondaryGroundPickupDown = 7;
    public static final int kSecondaryIntakeArmUpDown = 9;
    public static final int kSecondaryGroundPickupPlace = 10;
    public static final int kSecondaryClimberDown = 11;
    public static final int kSecondaryClimberUp = 12;

    //Wyatt likes tank joystick on right
    private Joystick turnPrimaryStick = new Joystick(0); //left joystick
    private Joystick tankPrimaryStick = new Joystick(1);//right joystick
    private Joystick secondaryControlPanel = new Joystick(2);

    public double getDriveTank() {
        double val = -tankPrimaryStick.getY();
        if (val < .15 && val > -.15) { // dead zone
            val = 0;
        }
        return val;
    }

    public double getDriveTurn() {
        double val = turnPrimaryStick.getX();
        if (val < .15 && val > -.15) {
            val = 0; // dead zone
        }
        return val;
    }

    public double getUnusedTank(){
        double val = -turnPrimaryStick.getY();
        if (val < .15 && val > -.15) { // dead zone
            val = 0;
        }
        return val;
    }


    public boolean getQuickTurn(){ return turnPrimaryStick.getRawButton(kTurnJoystickQuickTurn); }
    public boolean getShowBackCamera(){return turnPrimaryStick.getRawButton(kTurnJoystickCameraToggle);}
    public boolean getHatchOuttake() {return turnPrimaryStick.getTrigger();}
    public boolean getAutoOverride() {return turnPrimaryStick.getRawButton(kTurnJoystickAutoOverrideButton);}

    public boolean getShift(){return tankPrimaryStick.getRawButton(kTankJoystickShiftButton);}
    public boolean getLightFollow(){return tankPrimaryStick.getRawButton(kTankJoystickVisionDrive);}
    public boolean getCargoShoot() { return tankPrimaryStick.getTrigger();}

    public boolean getClimberUp() { return secondaryControlPanel.getRawButton(kSecondaryClimberUp); }
    public boolean getClimberDown(){ return secondaryControlPanel.getRawButton(kSecondaryClimberDown); }
    public boolean getCargoIntake() { return secondaryControlPanel.getTrigger();}
    public boolean getGroundIntakeDown() { return secondaryControlPanel.getRawButton(kSecondaryGroundPickupDown); }
    public boolean getGroundIntakePlace() { return secondaryControlPanel.getRawButton(kSecondaryGroundPickupPlace); }
    public boolean getGroundIntakeReady() { return secondaryControlPanel.getRawButton(kSecondaryGroundPickupReady); }
    public boolean getGroundIntakeStarting() { return secondaryControlPanel.getRawButton(8); }
    public boolean getRocket1Shoot(){ return secondaryControlPanel.getRawButton(kSecondaryRocket1); }
    public boolean getRocket2Shoot(){ return secondaryControlPanel.getRawButton(kSecondaryRocket2); }
    public boolean getIntakeArmToggle(){ return secondaryControlPanel.getRawButton(kSecondaryIntakeArmUpDown); }

    //////elevator//////
    /*
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
*/

}