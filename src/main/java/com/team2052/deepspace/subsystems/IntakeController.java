package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class IntakeController {
    private static IntakeController instance = null;
    public static IntakeController getInstance() {
        if (instance == null) {
            try {
                instance = new IntakeController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create IntakeController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }
    private IntakeController() {
        grabber = HatchGrabberController.getInstance();
    }
    private HatchGrabberController grabber = null;
    private VictorSPX intakeMotor = new VictorSPX (Constants.Intake.kIntakeMotorId);
    private VictorSPX clawTop = new VictorSPX(Constants.Intake.kClawTopMotor);
    private VictorSPX clawBottom = new VictorSPX(Constants.Intake.kClawBottomMotor);
    private Solenoid armInSolenoid = new Solenoid(Constants.Intake.kCargoInSolenoidId);
    private Solenoid armOutSolenoid = new Solenoid(Constants.Intake.kCargoOutSolenoidId);
    private final double armIntakeSpeed = -.85;
    private final double intakeTopClawSpeed = .6;
    private final double intakeBottomClawSpeed = -.6;
    private boolean isArmDown;
    private Timer rocketTimer = new Timer();
    private boolean isRocket1 = false;

    public void setArmDown (boolean isDown ) {
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!ARM " + isDown + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        armInSolenoid.set(!isDown);
        armOutSolenoid.set(isDown);
        isArmDown = isDown;
    } 

    public void setCargoIntake(boolean isPressed) {
        if(isPressed) {
            intakeMotor.set(ControlMode.PercentOutput, armIntakeSpeed);
            clawTop.set(ControlMode.PercentOutput, intakeTopClawSpeed );
            clawBottom.set(ControlMode.PercentOutput, intakeBottomClawSpeed );
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
            clawTop.set(ControlMode.PercentOutput, 0);
            clawBottom.set(ControlMode.PercentOutput, 0);
        }
    } //Sets cargo intake motor (In percent) to the speed you send it.

    public void setShootCargo(ShootSpeed shoot) {
        switch (shoot) {
            case ROCKET1:
                if(!isRocket1) {
                    rocketTimer.reset();
                    rocketTimer.start();
                    System.out.println("reseting rocket one: " + rocketTimer.get());
                    isRocket1 = true;
                }

                if(isRocket1 && rocketTimer.get() < .5) {
                    setArmDown(true);
                    if(rocketTimer.get() > .35) {
                        clawTop.set(ControlMode.PercentOutput, -.50);
                        clawBottom.set(ControlMode.PercentOutput, .50);
                    }
                    System.out.println("running motors: " + rocketTimer.get() + " bool: " +isRocket1);
                }else{
                    setArmDown(false);
                    clawTop.set(ControlMode.PercentOutput, 0);
                    clawBottom.set(ControlMode.PercentOutput, 0);
                }

                System.out.println("rocket1: " + isRocket1);
                break;
            case CARGOSHIP:
                clawTop.set(ControlMode.PercentOutput, - Constants.Intake.kOuttakeCargoShipSpeed);
                clawBottom.set(ControlMode.PercentOutput, Constants.Intake.kOuttakeCargoShipSpeed);
                if(isRocket1){
                    isRocket1 = false;
                    setArmDown(false);
                }
                break;
            case ROCKET2:
                clawTop.set(ControlMode.PercentOutput, -1);
                clawBottom.set(ControlMode.PercentOutput, 1);
                if(isRocket1){
                    isRocket1 = false;
                    setArmDown(false);
                }
                break;
            case AGAINSTWALL:
                clawTop.set(ControlMode.PercentOutput, -Constants.Intake.kOuttakeCargoShipSpeed - .1); //.55
                clawBottom.set(ControlMode.PercentOutput, Constants.Intake.kOuttakeCargoShipSpeed);
                if(isRocket1){
                    isRocket1 = false;
                    setArmDown(false);
                }
                break;
            case NONE:
            default:
                clawTop.set(ControlMode.PercentOutput, 0);
                clawBottom.set(ControlMode.PercentOutput, 0);
                if(isRocket1){
                    isRocket1 = false;
                    setArmDown(false);
                }
                break;
        }
    }

    private boolean lastIsPressed;

    public void toggleArmPosition(boolean isPressed) {
        if (isPressed && !lastIsPressed) { //first time we detected button press
            if (isArmDown) {
                setArmDown(false);
            } else {
                setArmDown(true);
            }
        }
        lastIsPressed = isPressed;
    }

    public void setHatchPlace (boolean isPressed) {
        grabber.setHatchPlace(isPressed);
    }

    public enum ShootSpeed {
        NONE,
        ROCKET1,
        ROCKET2,
        CARGOSHIP,
        AGAINSTWALL
    }
}
