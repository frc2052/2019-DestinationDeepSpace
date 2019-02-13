package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class BackLineFollowerController extends LineFollowerControllerBase {
    private static LineFollowerController instance = null;
    public static LineFollowerController getInstance() {
        if (instance == null) {
            try {
                instance = new LineFollowerController();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Light sensor follower: " + exc.getMessage());
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
