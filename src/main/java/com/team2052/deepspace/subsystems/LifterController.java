package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

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
    private int lifterButtonPressCount = 0;
    private boolean wasLastPressed = false;

    public void setLegsDown(boolean isPressed) {

        double timeLeft = Timer.getMatchTime(); //this SHOULD be the time remaining in the match

        if (!wasLastPressed){ //button state has changed, was up and is now down
            lifterButtonPressCount++; //keep track of how many times the button was pressed
        }

        //keep track of whether button is up or down
        wasLastPressed = true;

        if ((timeLeft < 30 && lifterButtonPressCount > 0) || lifterButtonPressCount > 10) {
            System.out.println("Lifter setting to " + isPressed);
            liftOutSolenoid.set(isPressed);
        }
    }

}
