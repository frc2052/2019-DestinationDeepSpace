package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeController {
    private static IntakeController instance = new IntakeController();
    public static IntakeController getInstance() {return instance;}


    private TalonSRX inTakeMotor = new TalonSRX (Constants.Intake.kIntakeMotorId);
    private Solenoid cargoIntakeSolenoid;
    private Solenoid hatchIntakeSolenoid;
    public void cargoIntake(double speed) {
        inTakeMotor.set(ControlMode.PercentOutput, speed);
    }
    private boolean cargoIntakeState;
    public boolean getCargoIntakeState() {
        return cargoIntakeState;
    }
    public void setCargoIntakeState(boolean val){
        cargoIntakeState = val;
    }
    private boolean hatchIntakeState;
    public boolean gethatchIntakeState() {
        return hatchIntakeState;
    }
    public void sethatchIntakeState(boolean val){
        hatchIntakeState = val;
    }

}
