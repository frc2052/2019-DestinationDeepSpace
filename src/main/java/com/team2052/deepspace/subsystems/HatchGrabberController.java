package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class HatchGrabberController {
    private static HatchGrabberController instance = null;
    public static HatchGrabberController getInstance() {
        if (instance == null) {
            try {
                instance = new HatchGrabberController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create HatchGrabberController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    private Solenoid hatchIntakeSolenoid = new Solenoid(Constants.Intake.kGrabber1SolenoidId);
    private Solenoid hatchOuttakeSolenoid = new Solenoid(Constants.Intake.kGrabber2SolenoidId);

    public void setHatchPlace (boolean isPressed) {
        isOpen = isPressed;
        if (isPressed) {
            hatchIntakeSolenoid.set(true);
            hatchOuttakeSolenoid.set(false);
        } else {
            hatchIntakeSolenoid.set(false);
            hatchOuttakeSolenoid.set(true);
        }
    }

    private boolean isOpen;

    public boolean getIsOpen () {
        return isOpen;
    }

}
