package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeController {
    private static IntakeController instance = new IntakeController();
    public static IntakeController getInstance() {return instance;}

    private TalonSRX intakeMotor = new TalonSRX (Constants.Intake.kIntakeMotorId);
    private Solenoid cargoIntakeSolenoid = new Solenoid(Constants.Intake.kCargoId);
    private Solenoid hatchIntakeSolenoid = new Solenoid(Constants.Intake.kHatchId);

    public void setCargoIntake(double speed) {
        intakeMotor.set(ControlMode.PercentOutput, speed);
    } //Sets cargo intake motor (In percent) to the speed you send it.

    private boolean cargoIntakeState;

    public void setNeutralCargoSpeed(){
        setCargoIntake(Constants.Intake.kNeutralSpeed);
    }
    public boolean getCargoIntakeState() {
        return cargoIntakeState;
    }

    public void setCargoIntakeState(boolean booling){
        cargoIntakeState = booling;
        cargoIntakeSolenoid.set(cargoIntakeState);
    } //Getter and setter for cargo state
    public void intake(){
        setCargoIntake(Constants.Intake.kIntakePercentSpeed);
    }
    public void outtake(){
        setCargoIntake(Constants.Intake.kOuttakePercentSpeed);
    }

    private boolean hatchIntakeState;

    public boolean getHatchIntakeState() {
        return hatchIntakeState;
    }

    public void setHatchIntakeState(boolean boolin) {
        hatchIntakeState = boolin;
        hatchIntakeSolenoid.set(hatchIntakeState);
    }

}
