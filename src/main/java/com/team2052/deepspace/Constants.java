package com.team2052.deepspace;


public class Constants {
    //All constant values for the robot code will go in this class.


    public class Controls{

    }
    public class DriveTrain{
        public static final int kDriveLeftMasterId = 1;
        public static final int kDriveLeftSlaveId = 2;
        public static final int kDriveRightMasterId = 4;
        public static final int kDriveRightSlaveId = 3;

        public static final int kVelocityControlSlot = 0;
        public static final int kCANBusConfigTimeoutMS = 10;
        public static final int kkTicksPerRot = 1024;
        public static final double kDriveWheelCircumferenceInches = 6.0 * Math.PI;
    }
    public class Intake {
        public static final int kIntakeMotorId = 1;
        public static final int kCargoId = 2;
        public static final int kHatchId = 3;
        public static final int kOuttakePercentSpeed = -30;
        public static final int kNeutralSpeed = 20;
        public static final int kIntakePercentSpeed = 50;
    }

    public class Intake{
        public static final int kLifterId = 1;
        public static final int kGrabberId = 2;
        public static final double kGrabTime = .5;
        public static final double kLiftTime = kGrabTime + .5;
    }

    public class Autonomous{ //all units for distances, velocity, and acceleration are in inches

    }
    public class Elevator{
        public static final int kElevatorMotorId = 8;
        public static final double kElevatorTicksPerRot = 256;
        public static final double kElevatorInchesPerRotation = 15;

        //////scoring heights//////
        public static final double kGroundCargoHeight =0;
        public static final double kHatchLevel1Height = 1;
        public static final double kHatchLevel2Height = 2;
        public static final double kHatchLevel3Height = 3;
        public static final double kCargoShipCargoHeight = 38;
        public static final double kRocketCargoHeight1 = 27.5;
        public static final double kRocketCargoHeight2 = 55.5;
        public static final double kRocketCargoHeight3 = 83.5;

        //////max heights//////
        public static final double kElevatorMaxHeight = 86;
        public static final double kElevatorMinHeight = 0;

        //////buttons//////
        public static final int kElevatorGroundGargoButton = 2;
        public static final int kElevatorHatch1Button = 3;
        public static final int kElevatorHatch2Button = 4;
        public static final int kElevatorHatch3Button = 5;
        public static final int kElevatorCargoShipCargoButton = 6;
        public static final int kElevatorCargo1Button = 7;
        public static final int kElevatorCargo2Button = 8;
        public static final int kElevatorCargo3Button = 9;
        public static final int kElevatorAdjustmenUpButton = 10;
        public static final int kElevatorAdjustmenDownButton = 11;
        public static final int kElevatorEmergencyUpButton = 12;
        public static final int kElevatorEmergencyDownButton = 13;

        //////emergency motor power/////
        public static final double kElevatorEmergencyUpPower = 0.4;
        public static final double kElevatorEmergencyHoldPower = 0.2;
    }


}
