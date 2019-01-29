package com.team2052.deepspace;

import com.team2052.deepspace.subsystems.DriveTrainController;
import edu.wpi.first.wpilibj.DigitalInput;

public class LightSensorFollowerTapeThingyThing {

    private double joystickSpeed = 0;

    private DriveTrainController driveTrain = null;
    private static LightSensorFollowerTapeThingyThing instance = null;

    public static LightSensorFollowerTapeThingyThing getInstance() {
        if (instance == null) {
            try {
                instance = new LightSensorFollowerTapeThingyThing();
            } catch (Exception exc) {
                System.out.println("DANGER: Failed to create Light sensor follower: " + exc.getMessage());
                exc.printStackTrace();
            }

        }

        return instance;
    }

    //light sensors
    private DigitalInput leftLightSensor = new DigitalInput(Constants.LightSensorFollowerTapeThingyThing.kLeftLightSensorid);
    private DigitalInput middleLightSensor = new DigitalInput(Constants.LightSensorFollowerTapeThingyThing.kMiddleLightSensorid);
    private DigitalInput rightLightSensor = new DigitalInput(Constants.LightSensorFollowerTapeThingyThing.kRightLightSensorid);


    public LightSensorFollowerTapeThingyThing() {//constructor with drive train instance
        driveTrain = DriveTrainController.getInstance();
    }


    public void setLightSensorMotorStates(double speed) {//defines the states (look at enum for explanation of FTF)
        boolean getLeftLightSensorState = leftLightSensor.get();
        boolean getMiddleLightSensorState = middleLightSensor.get();
        boolean getRightLightSensorState = rightLightSensor.get();
        System.out.println("left: " + getLeftLightSensorState + ", center: " + getMiddleLightSensorState + ", right: " + getRightLightSensorState);
        if (!getLeftLightSensorState && getMiddleLightSensorState && !getRightLightSensorState) {
            getLightSensorMotorTurn(LightSensorStateEnum.FTF);
        } else if (!getLeftLightSensorState && !getMiddleLightSensorState && getRightLightSensorState) {
            getLightSensorMotorTurn(LightSensorStateEnum.FFT);
        } else if (getLeftLightSensorState && !getMiddleLightSensorState && !getRightLightSensorState) {
            getLightSensorMotorTurn(LightSensorStateEnum.TFF);
        } else if (!getLeftLightSensorState && getMiddleLightSensorState && getRightLightSensorState) {
            getLightSensorMotorTurn(LightSensorStateEnum.FTT);
        } else if (getLeftLightSensorState && getMiddleLightSensorState && !getRightLightSensorState) {
            getLightSensorMotorTurn(LightSensorStateEnum.TTF);
        } else if (!getLeftLightSensorState && !getMiddleLightSensorState && !getRightLightSensorState) {
            getLightSensorMotorTurn(LightSensorStateEnum.FFF);
        }
        joystickSpeed = speed;
    }

    public void getLightSensorMotorTurn(LightSensorStateEnum directionEnum) {//sets the tank and the turn based on the sensor readings
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

