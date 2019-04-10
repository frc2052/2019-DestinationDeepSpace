package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class LifterController {
    private static LifterController instance = null;
    public static LifterController getInstance() {
        if (instance == null) {
            try {
                instance = new LifterController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create LifterController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private Solenoid liftOutSolenoid = new Solenoid(Constants.Lifter.kLifterOutSolenoidId);

    public void setLegsDown(boolean isPressed) {
        System.out.println("Lifter setting to " + isPressed);
        liftOutSolenoid.set(isPressed);
    }

}
