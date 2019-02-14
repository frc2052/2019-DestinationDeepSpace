package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class BackLineFollowerController extends LineFollowerControllerBase {
    private static BackLineFollowerController instance = null;
    public static BackLineFollowerController getInstance() {
        if (instance == null) {
            try {
                instance = new BackLineFollowerController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create BackLineFollowerController: " + exc.getMessage());
                exc.printStackTrace();
            }
        }
        return instance;
    }

    public BackLineFollowerController() {
        this.leftLightSensor = new DigitalInput(Constants.LineFollower.kBackLeftLightSensorId);
        this.middleLightSensor = new DigitalInput(Constants.LineFollower.kBackMiddleLightSensorId);
        this.rightLightSensor = new DigitalInput(Constants.LineFollower.kBackRightLightSensorId);
    }
}
