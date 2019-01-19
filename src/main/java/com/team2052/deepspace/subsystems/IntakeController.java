package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeController {
    private static IntakeController instance = new IntakeController();
    public static IntakeController getInstance() {return instance;}


    private TalonSRX inTakeMotor = new TalonSRX (Constants.Intake.kIntakeMotorId);
    private Solenoid openIntakeSolenoid;
    public void intake (double speed) {
        inTakeMotor.set(ControlMode.PercentOutput, speed);
    }
    private boolean intakeState;
    public boolean getIntakeState() {
        return intakeState;
    }
    public void setIntakeState(boolean val){
        intakeState = val;
    }

}
