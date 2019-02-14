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
        intake = IntakeController.getInstance();
        controls = Controls.getInstance();
        legClimberController = LegClimberController.getInstance();
        legClimberController.resetEncoders();
        driveTrain = DriveTrainController.getInstance();
        controlLoop.addLoopable(robotStateCalculator);
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
        AutoMode currentAutoMode = AutoModeFactory.getAutoMode(currentAutoModeDef, AutoModeSelector.getHab2Start());
        //use the instance to get direction and position
        robotStateCalculator.setStartDirection(currentAutoMode.getStartDirection().isForward);
        robotStateCalculator.resetRobotState(currentAutoMode.getLateralStartPosition().lateralOffset,currentAutoMode.getForwardOffset());
        //start running the auto mode
        autoModeRunner.start(currentAutoMode);
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        robotstate.outputToSmartDashboard();
        if(controls.getAutoOverride()){
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
            AutoMode preload = AutoModeFactory.getAutoMode((selected), AutoModeSelector.getHab2Start());
        }
    }

    private void driverControlled(){
        if (controls.getLightFollow() && lineFollower.getLineSensed()){ //if line follower sees/has seen line in last 2 seconds
            driveTrain.drive(lineFollower.getLightSensorMotorTurn(controls.getDriveTank())); //line sensor controls turning towards line
        } else {
            driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
        }
        robotstate.outputToSmartDashboard();
        driveTrain.setHighGear(controls.getShift());

        //legClimberController.printEncoder();


        VisionController.showBackPiCamera(controls.getShowBackCamera());

        //always pass the button for climb to the leg climber
        //it needs to keep track of how many times the button was pressed
        //pressed 10 times will allow us to climb even if the match isn't in its last 30 seconds
        legClimberController.setLegClimber(controls.getClimberUp());
        if (controls.getClimberDown()){
            legClimberController.lowerClimber();
        }

        intake.setCargoIntake(controls.getCargoIntake());
        intake.setHatchPlace(controls.getHatchOuttake());
        intake.toggleArmPosition(controls.getIntakeArmToggle());

        if (controls.getCargoShoot() && controls.getRocket1Shoot()) {
            intake.setShootCargo(IntakeController.ShootSpeed.ROCKET1);
        } else if(controls.getCargoShoot() && controls.getRocket2Shoot()) {
            intake.setShootCargo(IntakeController.ShootSpeed.ROCKET1);
        } else if (controls.getCargoShoot()) {
            intake.setShootCargo(IntakeController.ShootSpeed.CARGOSHIP);
        }

        groundIntake.pickupFromFloor(controls.getGroundIntakeDown());
        groundIntake.setUpClosed(controls.getGroundIntakeReady());
        groundIntake.placement(controls.getGroundIntakePlace());
    }
}
