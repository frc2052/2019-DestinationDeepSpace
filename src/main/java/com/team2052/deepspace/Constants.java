package com.team2052.deepspace;


import com.team2052.deepspace.auto.AutoMode;

public class Constants {
    //All constant values for the robot code will go in this class.


    public class Controls{
        public static final int kQuickTurnButton = 3;
        public static final int kShiftButton = 2;

        public static final int kElevatorGroundGargoButton = 2;
        public static final int kElevatorHatch1Button = 3;
        public static final int kElevatorHatch2Button = 4;
        public static final int kElevatorHatch3Button = 6;
        public static final int kElevatorCargoShipCargoButton = 6;
        public static final int kElevatorCargo1Button = 7;
        public static final int kElevatorCargo2Button = 8;
        public static final int kElevatorCargo3Button = 9;
        public static final int kElevatorAdjustmentUpButton = 10;
        public static final int kElevatorAdjustmentDownButton = 11;
        public static final int kElevatorEmergencyUpButton = 3;
        public static final int kElevatorEmergencyDownButton = 7;

        public static final int kautoOverrideButton = 2;
        public static final int kGroundIntakeButton = 1;
        public static final int kGroundOuttakeButton = 2;
        public static final int kGrabButton = 8;
        public static final int kLegClimberButton = 4;
        public static final int kLegClimberLowerButton = 8;

        public static final int kLightSensorFollowButton = 3;
    }

    public class DriveTrain{
        public static final int kDriveRightMasterId = 1;
        public static final int kDriveRightSlaveId = 2;
        public static final int kDriveRightSlave2Id = 3;
        public static final int kDriveLeftMasterId = 4;
        public static final int kDriveLeftSlaveId = 5;
        public static final int kDriveLeftSlave2Id = 6;


        public static final int kVelocityControlSlot = 0;
        public static final int kCANBusConfigTimeoutMS = 10;
        public static final int kTicksPerRot = 1024;
        public static final double kEncoderGearRatio = (1.0/3)*(20.0/64);
        public static final double kDriveWheelCircumferenceInches = 6.0 * Math.PI;

        public static final int kShiftInSolenoidID = 0;
        public static final int kShiftOutSolenoidID = 1;
    }
    public class Intake {
        public static final int kGroundIntakeMotor = 0;
        public static final int kClawTopMotor = 0;
        public static final int kClawBottomMotor = 0;
        public static final int kIntakeMotorId = 1;
        public static final int kGrabber2SolenoidId = 2;
        public static final int kGrabber1SolenoidId = 3;
        public static final int kCargoInId = 2;
        public static final int kCargoOutId = 3;
        public static final int kHatchInId = 3;
        public static final int kHatchOutId = 4;
        public static final int kOuttakePercentSpeed = -30;
        public static final int kNeutralSpeed = 20;
        public static final int kIntakePercentSpeed = 50;
        public static final double kGrabTime = .5;
        public static final double kLiftTime = kGrabTime + .5;
        public static final double kReleaseTime = .5;
        public static final double kEscapeTime = kReleaseTime + .5;


    }



    public static class Autonomous{ //all units for distances, velocity, and acceleration are in inches

        public static final double kturnSpeed = 6.0; //constant from 1-5     higher = faster
        public static final double kMaxAccel = 120; //how fast the robot accelerates and decelerates
        public static final double kLookaheadDistance = 30; //12-25 // changes how smooth it follows path. lower = curves back and forth/fishtail, higher = less accurate
        public static final double kA = 0.01 ; //0-.1 todo: test and see how robot responds
        public static final double kP = 0.0; //0-.1
        public static final double kTestVelocity = 7 * 12 * 1.0;
        public static final boolean kIsAutoHighGear = false;


        public static final double kMaxAutoVelocity = kIsAutoHighGear ? (13*12.0) : 7*12.0; //13 ft/s is high, 7 ft/s is low

        public static final long kloopPeriodMs = 50;
        public static final double kloopPeriodSec = kloopPeriodMs/1000.0; //int devision


        public static final int kNumOfFakePts = (int)((Constants.Autonomous.kLookaheadDistance * 1.5)/Constants.Autonomous.kMinPointSpacing); //how many extra point have we added after the last one?
        public static final double kTrackWidth = 28.0;
        public static final double kRequiredDistanceFromEnd = 3;
        public static final double kV = 1/(kMaxAutoVelocity);
        //todo: put ka and kp back here
        //pidf copied from 2017 needs testing
        public static final double kTp = 0.2;
        public static final double kTi = 0.0;
        public static final double kTd = 3.0;
        public static final double kTf = 0.3;

        public static final double kStartLeftInchOffset = -47;
        public static final double kStartRightInchOffset = -47;

        public static final double kMinVelocity = 0.05; //range 0-1: minimum amount of power to overcome static friction

        public static final double kMinPointSpacing = 6;
        public static final AutoMode.StartDirection defaultStartDirection = AutoMode.StartDirection.BACKWARD;
    }


    public  class LegClimber {

        //////ids//////
        public static final int kLegClimberSolenoid1id = 7;
        public static final int klegClimbersolenoid2id = 8;
        public static final int kLegClimberTalon1id = 7;

        public static final double kLegClimberMotorVelocity = 0.7;

        public static final double kEncoderTicksPerRotation = 1024;
        public static final double kClimbMotorRotations = 940/4.0;
        public static final int kClimberMotorDistance = 1438000;
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

        //////emergency motor power/////
        public static final double kElevatorEmergencyUpPower = 0.4;
        public static final double kElevatorEmergencyHoldPower = 0.2;
    }

    public class LineFollower {
        //////ports//////
        public static final int kLeftLightSensorId = 2;
        public static final int kMiddleLightSensorId = 1;
        public static final int kRightLightSensorId = 0;
        public static final int kBackLeftLightSensorId = 5;
        public static final int kBackMiddleLightSensorId = 4;
        public static final int kBackRightLightSensorId = 3;

        //////speeds//////
        public static final double kLightSensorTurnHardSpeedReduction = -1;
        public static final double kLightSensorTurnLightSpeedReduction = -0.8;
        public static final double kLightSensorMotorSpeed = 0.4;
    }


}
