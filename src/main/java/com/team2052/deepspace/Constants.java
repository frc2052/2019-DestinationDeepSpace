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

    public class Intake {

        public static final int kIntakeTalon1id = 5;

    }

    public  class LegClimber {

        public static final int kLegClimberTalon1id = 20;
        public static final double kLegClimberMotorVelocity = 0.7;
        public static final int kLegClimberButton = 5;
        public static final int kLegClimberSolenoid1id = 21;
        public static final int klegClimbersolenoid2id = 22;
    }

}
