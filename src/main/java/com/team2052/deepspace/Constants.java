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
        public static final double kturnSpeed = 4.0; //constant from 1-5     higher = faster

        public static final double kMaxVelocity = (10 * 12); //10 * 12
        public static final double kMaxAccel = 40;
        public static final long kloopPeriodMs = 50;
        public static final double kloopPeriodSec = kloopPeriodMs/1000.0; //int devision

        public static final double kLookaheadDistance = 15; //12-25
        public static final int kNumOfFakePts = (int)((Constants.Autonomous.kLookaheadDistance * 1.5)/Constants.Autonomous.kMinPointSpacing); //how many extra point have we added after the last one?
        public static final double kTrackWidth = 25;
        public static final double kRequiredDistanceFromEnd = 3;
        public static final double kV = 1/(kMaxVelocity+8);
        public static final double kA = 0.003; //0.002
        public static final double kP = 0.002; //0.002

        public static final double kMinVelocity = 0.05; //range 0-1: minimum amount of power to overcome static friction

        public static final double kMinPointSpacing = 6;

    }
}
