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


    public class Autonomous{ //all units for distances, velocity, and acceleration are in inches

    }
}