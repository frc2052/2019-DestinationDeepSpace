package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.Constants;
import com.team2052.deepspace.DriveSignal;
import com.team2052.deepspace.LightSensorFollowerTapeThingyThing;
import com.team2052.deepspace.subsystems.DriveTrainController;
import edu.wpi.first.wpilibj.Ultrasonic;

public class FollowLineAction {

    Ultrasonic ultrasonicSensor = new Ultrasonic(1, 1);
    double range = ultrasonicSensor.getRangeInches();
    private DriveTrainController driveTrain = null;

    public void done() {

    }

    public boolean isFinished() {

        return (range <= 2);
    }

    public void start() {

    }

    public void update() {

    }


    public void getLightSensorMotorTurn(LightSensorFollowerTapeThingyThing.LightSensorStateEnum directionEnum) {//sets the tank and the turn based on the sensor readings
        System.out.println(directionEnum);
        switch (directionEnum) {
            case FTF: //only the middle sensor is on so it goes forward
                driveTrain.drive(new DriveSignal(joystickSpeed, joystickSpeed));
                break;
            case FFT: //only right sensor is on so it goes right to get the middle sensor on
                driveTrain.drive(new DriveSignal(joystickSpeed, joystickSpeed * Constants.LightSensorFollowerTapeThingyThing.kLightSensorTurnHardSpeed));
                break;
            case TFF: //only left sensor is on so it goes left to get the middle sensor on
                driveTrain.drive(new DriveSignal(joystickSpeed * Constants.LightSensorFollowerTapeThingyThing.kLightSensorTurnHardSpeed, joystickSpeed));
                break;
            case FTT: //both right and middle are on so it drives slower to the right to get only the middle sensor on
                driveTrain.drive(new DriveSignal(joystickSpeed, joystickSpeed * Constants.LightSensorFollowerTapeThingyThing.kLightSensorTurnLightSpeed));
                break;
            case TTF: //both left and middle are on so it dirves slower to the right to get only the middle sensor on
                driveTrain.drive(new DriveSignal(joystickSpeed * Constants.LightSensorFollowerTapeThingyThing.kLightSensorTurnLightSpeed, joystickSpeed));
                break;
            case FFF:
                driveTrain.drive(new DriveSignal(joystickSpeed, joystickSpeed));
        }
    }

    public enum LightSensorStateEnum {// first T/F is the first sensor reading etc.
        TTF,
        TFF,
        FTT,
        FTF,
        FFT,
        FFF,
    }
}
