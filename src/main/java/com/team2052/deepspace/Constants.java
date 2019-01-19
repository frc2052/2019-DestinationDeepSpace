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


    public class Autonomous{ //all units for distances, velocity, and acceleration are in inches

    }
    public class Elevator{
        public static final int kElevatorMotorId = 8;
        public static final double kElevatorTicksPerRot = 256;
        public static final double kElevatorInchesPerRotation = 15;
        public static final double kGroundCargoHeight =0;
        public static final double kHatchLevel1Height = 1;
        public static final double kHatchLevel2Height = 2;
        public static final double kHatchLevel3Height = 3;
        public static final double kCargoshipCargoHeight = 38;
        public static final double kRocketCargoHeight1 = 27.5;
        public static final double kRocketCargoHeight2 = 55.5;
        public static final double kRocketCargoHeight3 = 83.5;


    }
}
