package com.team2052.deepspace.subsystems;

import com.team2052.deepspace.Constants;
import com.team2052.lib.DriveSignal;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public abstract class LineFollowerControllerBase {
    //light sensors
    protected DigitalInput leftLightSensor;
    protected DigitalInput middleLightSensor;
    protected DigitalInput rightLightSensor;

    private LineFollowerController.LastSensorDetected lastSensor = LineFollowerController.LastSensorDetected.NONE;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;
    private double lastTimeISawATape = 0.0;
    private double joystickSpeed = 0;
    protected boolean reversed = false;

    public boolean getLineSensed(){
        boolean recentlySeen = false;
        if(Timer.getFPGATimestamp() - lastTimeISawATape < 2.5 && lastSensor != LineFollowerController.LastSensorDetected.NONE){
            recentlySeen = true;
        }
        return getLeftSensor() || getMiddleSensor() || getRightSensor() || recentlySeen;
    }

    public boolean getLeftSensor(){
        if(reversed){
            return !leftLightSensor.get();
        }else{
            return  leftLightSensor.get();
        }
    }

    public boolean getMiddleSensor(){
        if(reversed){
            return !middleLightSensor.get();
        }else{
            return  middleLightSensor.get();
        }
    }

    public boolean getRightSensor(){
        if(reversed){
            return !rightLightSensor.get();
        }else{
            return  rightLightSensor.get();
        }
    }

    public DriveSignal getLightSensorMotorTurn(double speed) {//defines the states (look at enum for explanation of FTF)
        boolean getLeftLightSensorState = getLeftSensor();
        boolean getMiddleLightSensorState = getMiddleSensor();
        boolean getRightLightSensorState = getRightSensor();

        joystickSpeed = speed;

        System.out.println("left: " + getLeftLightSensorState + ", center: " + getMiddleLightSensorState + ", right: " + getRightLightSensorState + " last seen tape " + (Timer.getFPGATimestamp() - lastTimeISawATape) + " " + getLineSensed());
        if(getLeftLightSensorState) {
            lastTimeISawATape = Timer.getFPGATimestamp();
            lastSensor = LineFollowerController.LastSensorDetected.LEFT;
            isTurningLeft = true;
            isTurningRight = false;
            return new DriveSignal(joystickSpeed * Constants.LineFollower.kLightSensorTurnHardSpeedReduction, joystickSpeed);
        } else if(getRightLightSensorState) {
            lastTimeISawATape = Timer.getFPGATimestamp();
            lastSensor = LineFollowerController.LastSensorDetected.RIGHT;
            isTurningLeft = false;
            isTurningRight = true;
            return new DriveSignal(joystickSpeed, joystickSpeed * Constants.LineFollower.kLightSensorTurnHardSpeedReduction);
        } else if(getMiddleLightSensorState) {
            lastTimeISawATape = Timer.getFPGATimestamp();
            lastSensor = LineFollowerController.LastSensorDetected.CENTER;
            return (new DriveSignal(joystickSpeed, joystickSpeed));
        }
        else {// if the tape is in the dead zone
            if ((isTurningLeft && lastSensor == LineFollowerController.LastSensorDetected.LEFT) || (isTurningRight && lastSensor == LineFollowerController.LastSensorDetected.RIGHT)){
                return (new DriveSignal(joystickSpeed, joystickSpeed));
            } else if (isTurningLeft && lastSensor == LineFollowerController.LastSensorDetected.CENTER) {
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

    public void resetLineSensor(){
        lastTimeISawATape = 0.0;
        lastSensor = LastSensorDetected.NONE;
    }
    public enum LastSensorDetected {
        NONE,
        RIGHT,
        LEFT,
        CENTER,
    }
}