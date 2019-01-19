package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;

public class ElevatorController {

    private TalonSRX elevatorMotor = new TalonSRX(Constants.Elevator.kElevatorMotorId);
    private static ElevatorController singleElevatorControllerInstance = new ElevatorController();
    public static ElevatorController getInstance() {
        return singleElevatorControllerInstance;
    }

    private int goalElevatorInches;
    private ElevatorController(){
        elevatorMotor.setNeutralMode(NeutralMode.Brake);
        elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
    }
    public void zeroSensor(){
        elevatorMotor.setSelectedSensorPosition(0, 0, Constants.DriveTrain.kCANBusConfigTimeoutMS);
    }
    public double getHeightInches() {
        int encoderPos = elevatorMotor.getSelectedSensorPosition(0);
        double revolutions = encoderPos / (double)Constants.Elevator.kElevatorTicksPerRot;
        double inches = revolutions * Constants.Elevator.kElevatorInchesPerRotation;
        return inches;
    }
    public double getElevatorPresetsHeights(ElevatorPresets PosEnum){
        switch (PosEnum){
            case GROUND_CARGO:
                return Constants.Elevator.kGroundCargoHeight;
            case HATCH_LEVEL1:
                return Constants.Elevator.kHatchLevel1Height;
            case HATCH_LEVEL2:
                return Constants.Elevator.kHatchLevel2Height;
            case HATCH_LEVEL3:
                return Constants.Elevator.kHatchLevel3Height;
            case CARGOSHIP_CARGO:
                return Constants.Elevator.kCargoshipCargoHeight;
            case ROCKET_CARGO1:
                return Constants.Elevator.kRocketCargoHeight1;
            case ROCKET_CARGO2:
                return Constants.Elevator.kRocketCargoHeight2;
            case ROCKET_CARGO3:
                return Constants.Elevator.kRocketCargoHeight3;
        }
    return 0.0;
    }
    public enum ElevatorPresets {
        GROUND_CARGO,
        HATCH_LEVEL1,
        HATCH_LEVEL2,
        HATCH_LEVEL3,
        CARGOSHIP_CARGO,
        ROCKET_CARGO1,
        ROCKET_CARGO2,
        ROCKET_CARGO3,

    }
}
