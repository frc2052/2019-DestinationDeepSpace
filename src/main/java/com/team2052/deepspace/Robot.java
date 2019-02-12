package com.team2052.deepspace;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.AutoModeFactory;
import com.team2052.deepspace.auto.AutoModeRunner;
import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.subsystems.*;
import com.team2052.lib.ControlLoop;
import com.team2052.lib.DriveHelper;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private GroundIntakeController groundIntake = null;
    private DriveHelper driveHelper = null;
    private IntakeController intake = null;
    private Controls controls = null;
    private DriveTrainController driveTrain = null;
    private ElevatorController elevator = null;
    private LegClimberController legClimberController = null;
    private LineFollowerController lineFollower = null;
    private RobotState robotstate = RobotState.getInstance();
    private RobotStateCalculator robotStateCalculator = RobotStateCalculator.getInstance();
    private AutoModeRunner autoModeRunner = new AutoModeRunner();
    private ControlLoop controlLoop = new ControlLoop(Constants.Autonomous.kloopPeriodSec);
    private Compressor compressor = null;
    private VisionController visionController = null;




    @Override
    public void robotInit() {
        groundIntake = GroundIntakeController.getInstance();
        driveHelper = new DriveHelper();
       // intake = IntakeController.getInstance();
        controls = Controls.getInstance();
        legClimberController = LegClimberController.getInstance();
        legClimberController.resetEncoders();
        driveTrain = DriveTrainController.getInstance();
       // elevator = ElevatorController.getInstance();
       // elevator.zeroSensor();
        controlLoop.addLoopable(robotStateCalculator);
        controlLoop.addLoopable(groundIntake);
        visionController = VisionController.getInstance();

        lineFollower = LineFollowerController.getInstance();
        try {
            compressor = new Compressor();
            compressor.setClosedLoopControl(true);
        } catch (Exception exc) {
            System.out.println("DANGER: No compressor!");
        }

        AutoModeSelector.putToShuffleBoard();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {

    }

    /**
        This function called once when autonomous starts
     */
    @Override
    public void autonomousInit() {
        controlLoop.start();
        driveTrain.zeroGyro();
        //get the enum for the selected automode
        AutoModeSelector.AutoModeDefinition currentAutoModeDef = AutoModeSelector.getSelectedAutomode();
        //ask the factory to create an instance (if not already created)
        AutoMode currentAutoMode = AutoModeFactory.getAutoMode(currentAutoModeDef);
        //use the instance to get direction and position
        robotStateCalculator.setStartDirection(currentAutoMode.getStartDirection().isForward);
        robotStateCalculator.resetRobotState(currentAutoMode.getStartPosition().lateralOffset,0);
        //start running the auto mode
        autoModeRunner.start(currentAutoMode);
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        robotstate.outputToSmartDashboard();
        if(controls.autoOverride()){
            autoModeRunner.stop();
            driveTrain.stop();
        }
        System.out.println("AUTO IS DONE?: " + autoModeRunner.isAutodone());

        if(autoModeRunner.isAutodone()){
            driverControlled();
        }
    }

    /**
     This function called once when teleoperated starts
     */
    @Override
    public void teleopInit(){
        robotStateCalculator.resetRobotState();
        controlLoop.start();
        driveTrain.zeroGyro();
        legClimberController.resetEncoders();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        driverControlled();
    }


    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    @Override
    public void disabledPeriodic(){
        autoModeRunner.stop();
        controlLoop.stop();
        driveTrain.stop();
        AutoModeSelector.AutoModeDefinition selected = AutoModeSelector.getSelectedAutomode();
        if (selected != null)
        {
            //force the auto mode to preload so that all paths are calculated before AutoInit
            AutoMode preload = AutoModeFactory.getAutoMode((selected));
        }
    }

    private void driverControlled(){
        if (controls.getLightFollow()){
            if(lineFollower.getLineSensed()){
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(controls.getDriveTank()));
            }else if (visionController.isTarget()){
                driveTrain.drive(visionController.getMotorOutput());
            } else {
                driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
            }
        } else {
            driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
        }
        robotstate.outputToSmartDashboard();
        driveTrain.setHighGear(controls.getShift());

        //legClimberController.printEncoder();


        if (controls.legClimber()){
            legClimberController.setLegClimber(controls.legClimber());
        } else if (controls.lowerClimber()){
            legClimberController.lowerClimber();
        }else {
            legClimberController.stopClimber();
        }

        /*
        if(controls.getIntake()){
            intake.cargoIntake();

        } else {
            intake.cargoNeutral();
        }

        intake.grab(controls.getGrab());


        if (controls.getElevatorGroundCargo()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.GROUND_CARGO);
        } else if (controls.getElevatorHatch1()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.HATCH_LEVEL1);
        } else if (controls.getElevatorHatch2()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.HATCH_LEVEL2);
        } else if (controls.getElevatorHatch3()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.HATCH_LEVEL3);
        } else if (controls.getElevatorCargoShipCargo()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.CARGOSHIP_CARGO);
        } else if (controls.getElevatorRocketCargo1()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.ROCKET_CARGO1);
        }else if (controls.getElevatorRocketCargo2()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.ROCKET_CARGO2);
        }else if (controls.getElevatorRocketCargo3()) {
            elevator.setTarget(ElevatorController.ElevatorPresets.ROCKET_CARGO3);
        }

        elevator.setElevatorAdjustmentUp(controls.getElevatorAdjustmentUp());
        elevator.setElevatorAdjustmentDown(controls.getElevatorAdjustmentDown());
        elevator.setEmergencyUp(controls.getElevatorEmergencyUp());
        elevator.setEmergencyDown(controls.getElevatorEmergencyDown());
        */
    }
}
