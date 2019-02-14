package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeController {
    private static IntakeController instance = new IntakeController();
    public static IntakeController getInstance() {return instance;}

    private TalonSRX intakeMotor = new TalonSRX (Constants.Intake.kIntakeMotorId);
    private TalonSRX clawTop = new TalonSRX(Constants.Intake.kClawTopMotor);
    private TalonSRX clawBottom = new TalonSRX(Constants.Intake.kClawBottomMotor);
    private Solenoid armInSolenoid = new Solenoid(Constants.Intake.kCargoInSolenoidId);
    private Solenoid armOutSolenoid = new Solenoid(Constants.Intake.kCargoOutSolenoidId);
    private Solenoid hatchIntakeSolenoid = new Solenoid(Constants.Intake.kHatchInId);
    private Solenoid hatchOuttakeSolenoid = new Solenoid(Constants.Intake.kHatchOutId);
    private final double armIntakeSpeed = .6;
    private final double intakeTopClawSpeed = .6;
    private final double intakeBottomClawSpeed = -.6;
    private boolean isArmDown;

    public void setArmDown (boolean isDown ) {
        armInSolenoid.set(isDown);
        armOutSolenoid.set(!isDown);
        isArmDown = isDown;
    } 

    public void setCargoIntake(boolean isPressed) {
        if(isPressed) {
            intakeMotor.set(ControlMode.PercentOutput, armIntakeSpeed);
            clawTop.set(ControlMode.PercentOutput, intakeTopClawSpeed );
            clawBottom.set(ControlMode.PercentOutput, intakeBottomClawSpeed );
            setArmDown(true);
        } else {
            intakeMotor.set(ControlMode.PercentOutput, 0);
            clawTop.set(ControlMode.PercentOutput, 0);
            clawBottom.set(ControlMode.PercentOutput, 0);
            setArmDown(false);
        }
    } //Sets cargo intake motor (In percent) to the speed you send it.

    public void setShootCargo(ShootSpeed shoot) {
        switch (shoot) {
            case ROCKET1:
                clawTop.set(ControlMode.PercentOutput, .25);
                clawBottom.set(ControlMode.PercentOutput, -.25);
                break;
            case CARGOSHIP:
                clawTop.set(ControlMode.PercentOutput, .35);
                clawBottom.set(ControlMode.PercentOutput, -.35);
                break;
            case ROCKET2:
                clawTop.set(ControlMode.PercentOutput, .50);
                clawBottom.set(ControlMode.PercentOutput, -.50);
                break;
            default:
                clawTop.set(ControlMode.PercentOutput, 0);
                clawBottom.set(ControlMode.PercentOutput, 0);
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
        if (isPressed) {
            hatchIntakeSolenoid.set(true);
            hatchOuttakeSolenoid.set(false);
        } else {
            hatchIntakeSolenoid.set(false);
            hatchOuttakeSolenoid.set(true);
        }
    }

    public enum ShootSpeed {
        NONE,
        ROCKET1,
        ROCKET2,
        CARGOSHIP
    }
}
