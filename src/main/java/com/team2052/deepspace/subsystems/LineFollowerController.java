package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import com.team2052.lib.DriveSignal;
import com.team2052.lib.KnightTimer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;

public class LineFollowerController {

    private double joystickSpeed = 0;

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

    //light sensors
    private DigitalInput leftLightSensor = new DigitalInput(Constants.LineFollower.kLeftLightSensorId);
    private DigitalInput middleLightSensor = new DigitalInput(Constants.LineFollower.kMiddleLightSensorId);
    private DigitalInput rightLightSensor = new DigitalInput(Constants.LineFollower.kRightLightSensorId);
    private LastSensorDetected lastSensor = LastSensorDetected.NONE;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;
    private double lastTimeISawATape = 0.0;



    public LineFollowerController() {//constructor with drive train instance
    }

    public boolean getLineSensed(){
        boolean recentlySeen = false;
        if(DriverStation.getInstance().getMatchTime() - lastTimeISawATape < 2.5 && lastSensor != LastSensorDetected.NONE){
            recentlySeen = true;
        }
        return leftLightSensor.get() || middleLightSensor.get() || rightLightSensor.get() || recentlySeen;
    }


    public DriveSignal getLightSensorMotorTurn(double speed) {//defines the states (look at enum for explanation of FTF)
        boolean getLeftLightSensorState = leftLightSensor.get();
        boolean getMiddleLightSensorState = middleLightSensor.get();
        boolean getRightLightSensorState = rightLightSensor.get();
        joystickSpeed = speed;

        System.out.println("left: " + getLeftLightSensorState + ", center: " + getMiddleLightSensorState + ", right: " + getRightLightSensorState);
       /* if (!getLeftLightSensorState && getMiddleLightSensorState && !getRightLightSensorState) {
            return (new DriveSignal(joystickSpeed, joystickSpeed));
        } else if (!getLeftLightSensorState && !getMiddleLightSensorState && getRightLightSensorState) {
            return(new DriveSignal(joystickSpeed, joystickSpeed * Constants.LineFollower.kLightSensorTurnHardSpeedReduction));
        } else if (getLeftLightSensorState && !getMiddleLightSensorState && !getRightLightSensorState) {
            return (new DriveSignal(joystickSpeed * Constants.LineFollower.kLightSensorTurnHardSpeedReduction, joystickSpeed));
        } else if (!getLeftLightSensorState && getMiddleLightSensorState && getRightLightSensorState) {
            return(new DriveSignal(joystickSpeed, joystickSpeed * Constants.LineFollower.kLightSensorTurnLightSpeedReduction));
        } else if (getLeftLightSensorState && getMiddleLightSensorState && !getRightLightSensorState) {
            return(new DriveSignal(joystickSpeed * Constants.LineFollower.kLightSensorTurnLightSpeedReduction, joystickSpeed));
        } else if (!getLeftLightSensorState && !getMiddleLightSensorState && !getRightLightSensorState) {
            return (new DriveSignal(joystickSpeed, joystickSpeed));
        }else {
            return new DriveSignal(0.0, 0.0);
        }
        */
        if(getLeftLightSensorState) {
            lastTimeISawATape = DriverStation.getInstance().getMatchTime();
            lastSensor = LastSensorDetected.LEFT;
            isTurningLeft = true;
            isTurningRight = false;
            return new DriveSignal(joystickSpeed * Constants.LineFollower.kLightSensorTurnHardSpeedReduction, joystickSpeed);
        } else if(getRightLightSensorState) {
            lastTimeISawATape = DriverStation.getInstance().getMatchTime();
            lastSensor = LastSensorDetected.RIGHT;
            isTurningLeft = false;
            isTurningRight = true;
            return new DriveSignal(joystickSpeed, joystickSpeed * Constants.LineFollower.kLightSensorTurnHardSpeedReduction);
        } else if(getMiddleLightSensorState) {
            lastTimeISawATape = DriverStation.getInstance().getMatchTime();
            lastSensor = LastSensorDetected.CENTER;
            return (new DriveSignal(joystickSpeed, joystickSpeed));
        }
        else {// if the tape is in the dead zone
            if ((isTurningLeft && lastSensor == LastSensorDetected.LEFT) || (isTurningRight && lastSensor == LastSensorDetected.RIGHT)){
                return (new DriveSignal(joystickSpeed, joystickSpeed));
            } else if (isTurningLeft && lastSensor == LastSensorDetected.CENTER) {
                isTurningLeft = false;
                isTurningRight = true;
                return(new DriveSignal(joystickSpeed * Constants.LineFollower.kLightSensorTurnLightSpeedReduction, joystickSpeed));
            } else if (isTurningRight) {
                isTurningLeft = true;
                isTurningRight = false;
                return(new DriveSignal(joystickSpeed, joystickSpeed * Constants.LineFollower.kLightSensorTurnLightSpeedReduction));
            } else {
                return DriveSignal.NEUTRAL;
            }

        }


    }

    public enum LastSensorDetected {
        NONE,
        RIGHT,
        LEFT,
        CENTER,
    }


    /*public DriveSignal getLightSensorMotorTurn(LightSensorStateEnum directionEnum) {//sets the tank and the turn based on the sensor readings
        System.out.println(directionEnum);
        switch (directionEnum) {
            case FTF: //only the middle sensor is on so it goes forward
            case FFT: //only right sensor is on so it goes right to get the middle sensor on
            case TFF: //only left sensor is on so it goes left to get the middle sensor on
            case FTT: //both right and middle are on so it drives slower to the right to get only the middle sensor on
            case TTF: //both left and middle are on so it dirves slower to the right to get only the middle sensor on
            case FFF:
            default :
                return new DriveSignal(0.0, 0.0);
        }
    }

    public enum LightSensorStateEnum {// first T/F is the first sensor reading etc.
        TTF,
        TFF,
        FTT,
        FTF,
        FFT,
        FFF,
    }*/
}

