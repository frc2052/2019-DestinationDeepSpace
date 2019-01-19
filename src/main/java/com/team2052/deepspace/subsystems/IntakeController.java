package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;

public class IntakeController {

    private static IntakeController singleIntakeControllerInstance = new IntakeController();
    public static IntakeController getInstance() {
        return singleIntakeControllerInstance;
    }


    private TalonSRX talon1 = new TalonSRX(Constants.Intake.kIntakeTalon1id);

    public void intake(double speed) {
        talon1.set(ControlMode.PercentOutput, speed);
    }



}
