package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeController {
    private static IntakeController instance = new IntakeController();
    public static IntakeController getInstance() {return instance;}

    private TalonSRX intakeMotor = new TalonSRX (Constants.Intake.kIntakeMotorId);
    private Solenoid cargoIntakeSolenoid = new Solenoid(Constants.Intake.kCargoInId);
    private Solenoid cargoOuttakeSolenoid = new Solenoid(Constants.Intake.kCargoOutId);
    private Solenoid hatchIntakeSolenoid = new Solenoid(Constants.Intake.kHatchId);

    public void setCargoIntake(double speed, boolean intake) {
        intakeMotor.set(ControlMode.PercentOutput, speed);
        cargoIntakeSolenoid.set(intake);
    } //Sets cargo intake motor (In percent) to the speed you send it.

    private boolean cargoIntakeState;
    public boolean getCargoIntakeState() {
        return cargoIntakeState;
    }
    public void setCargoIntakeState(boolean state){
        cargoIntakeState = state;
        cargoIntakeSolenoid.set(cargoIntakeState);
    } //Getter and setter for cargo state

    public void cargoNeutral(){
        setCargoIntake(Constants.Intake.kNeutralSpeed, false);
    }
    public void cargoIntake(){
        setCargoIntake(Constants.Intake.kIntakePercentSpeed, true);

    }

    private boolean isCargoClosed = false;
    private boolean lastPressedState;
    public void grab(Boolean isPressed){
        if (isPressed && !lastPressedState){  //it is pressed now, but wasn't pressed on the last loop
            //need to change the state of the grabber
            if (!isCargoClosed) {
                cargoGrab();
            } else {
                cargoOuttake();
            }
        }
        lastPressedState = isPressed;
    }

    public void cargoGrab(){
        cargoOuttakeSolenoid.set(true);
        isCargoClosed = true;
    }
    public void cargoOuttake(){
        cargoOuttakeSolenoid.set(false);
        isCargoClosed = false;
    }

    private boolean hatchIntakeState;

    public boolean getHatchIntakeState() {
        return hatchIntakeState;
    }

    public void setHatchIntakeState(boolean state) {
        hatchIntakeState = state;
        hatchIntakeSolenoid.set(hatchIntakeState);
    } //Getter and Setter for the HatchState
}
