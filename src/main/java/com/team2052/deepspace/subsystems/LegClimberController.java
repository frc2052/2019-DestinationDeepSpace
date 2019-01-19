package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class LegClimberController {

        private static LegClimberController singleLegClimberControllerInstance = new LegClimberController();
        public static LegClimberController getInstance() { return singleLegClimberControllerInstance; }

        private TalonSRX legClimberMotor = new TalonSRX(Constants.LegClimber.kLegClimberTalon1id);
        //with assistance
        private Solenoid LegClimberSolenoid1 = new Solenoid(Constants.LegClimber.kLegClimberSolenoid1id);
        private Solenoid LegClimberSolenoid2 = new Solenoid(Constants.LegClimber.klegClimbersolenoid2id);

        public void setLegClimber(boolean on) {
            if (on){
                legClimberMotor.set(ControlMode.PercentOutput, Constants.LegClimber.kLegClimberMotorVelocity);
                //with Assistance
                LegClimberSolenoid1.set(true);
                LegClimberSolenoid2.set(false);

            }
            else {
                legClimberMotor.set(ControlMode.PercentOutput, 0.0);
                
            }
    }
}
