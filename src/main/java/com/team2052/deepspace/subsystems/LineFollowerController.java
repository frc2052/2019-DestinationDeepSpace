package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class LineFollowerController extends LineFollowerControllerBase {
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

    public LineFollowerController() {
        this.leftLightSensor = new DigitalInput(Constants.LineFollower.kLeftLightSensorId);
        this.middleLightSensor = new DigitalInput(Constants.LineFollower.kMiddleLightSensorId);
        this.rightLightSensor = new DigitalInput(Constants.LineFollower.kRightLightSensorId);
        reversed = true;
    }
}

