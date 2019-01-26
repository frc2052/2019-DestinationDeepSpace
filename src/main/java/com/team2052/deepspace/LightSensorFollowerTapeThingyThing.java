package com.team2052.deepspace;

import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.deepspace.subsystems.ElevatorController;
import edu.wpi.first.wpilibj.DigitalInput;

import java.awt.*;

public class LightSensorFollowerTapeThingyThing {

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
    private DigitalInput leftLightSensor = new DigitalInput(Constants.LightSensorFollowerTabeThingyThing.kLeftLightSensorid);
    private DigitalInput middleLightSensor = new DigitalInput(Constants.LightSensorFollowerTabeThingyThing.kMiddleLightSensorid);
    private DigitalInput rightLightSensor = new DigitalInput(Constants.LightSensorFollowerTabeThingyThing.kRightLightSensorid);

    //booleans to get the light sensor values
    private boolean getLeftLightSensorState = leftLightSensor.get();
    private boolean getMiddleLightSensorState = middleLightSensor.get();
    private boolean getRightLightSensorState = rightLightSensor.get();

    public LightSensorFollowerTapeThingyThing() {//constructor with drive train instance
        driveTrain = DriveTrainController.getInstance();

    }


    public void setLighSensorMotorStates(){//defines the states (look at enum for explanation of FTF)
        if(!getLeftLightSensorState && getMiddleLightSensorState && !getRightLightSensorState){
            getLightSensorMotorTurn(LightSensorStateEnum.FTF);
        } else if (!getLeftLightSensorState && !getMiddleLightSensorState && getRightLightSensorState){
            getLightSensorMotorTurn(LightSensorStateEnum.FFT);
        } else if (getLeftLightSensorState && !getMiddleLightSensorState && !getRightLightSensorState){
            getLightSensorMotorTurn(LightSensorStateEnum.TFF);
        } else if (!getLeftLightSensorState && getMiddleLightSensorState && getRightLightSensorState){
            getLightSensorMotorTurn(LightSensorStateEnum.FTT);
        } else if (getLeftLightSensorState && getMiddleLightSensorState && !getRightLightSensorState){
            getLightSensorMotorTurn(LightSensorStateEnum.TTF);
        }
    }
    public void getLightSensorMotorTurn(LightSensorStateEnum directionEnum){//sets the tank and the turn based on the sensor readings
        switch(directionEnum){
            case FTF: //only the middle sensor is on so it goes forward
                driveTrain.drive(Constants.LightSensorFollowerTabeThingyThing.kLightSensorFollowSpeed, 0.0);
                break;
            case FFT: //only right sensor is on so it goes right to get the middle sensor on
                driveTrain.drive(Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnTankSpeed, Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnHardSpeed);
                break;
            case TFF: //only left sensor is on so it goes left to get the middle sensor on
                driveTrain.drive(Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnTankSpeed, -Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnHardSpeed);
                break;
            case FTT: //both right and middle are on so it drives slower to the right to get only the middle sensor on
                driveTrain.drive(Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnTankSpeed, Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnLightSpeed);
                break;
            case TTF: //both left and middle are on so it dirves slower to the right to get only the middle sensor on
                driveTrain.drive(Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnTankSpeed, -Constants.LightSensorFollowerTabeThingyThing.kLightSensorTurnLightSpeed);
                break;
        }
    }

    public enum LightSensorStateEnum{// first T/F is the first sensor reading etc.
        TTF,
        TFF,
        FTT,
        FTF,
        FFT,
    }
}
