package com.team2052.deepspace;

import com.team2052.deepspace.auto.AutoModeRunner;
import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.auto.actions.Action;
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
    private BackLineFollowerController backLineFollower = null;
    private RobotState robotstate = RobotState.getInstance();
    private RobotStateCalculator robotStateCalculator = RobotStateCalculator.getInstance();
    private AutoModeRunner autoModeRunner = null;
    private ControlLoop controlLoop = new ControlLoop(Constants.Autonomous.kloopPeriodSec);
    private ControlLoop groundIntakeLooper = new ControlLoop(Constants.Autonomous.kloopPeriodSec);
    private Compressor compressor = null;
    private VisionController visionController = null;



    @Override
    public void robotInit() {
        groundIntake = GroundIntakeController.getInstance();
        if (groundIntake != null) {
            groundIntakeLooper.addLoopable(groundIntake);
        }
        driveHelper = new DriveHelper();
        intake = IntakeController.getInstance();
        controls = Controls.getInstance();
        legClimberController = LegClimberController.getInstance();
        legClimberController.resetEncoders();
        driveTrain = DriveTrainController.getInstance();
        controlLoop.addLoopable(robotStateCalculator);
        visionController = VisionController.getInstance();
        lineFollower = LineFollowerController.getInstance();
        backLineFollower = BackLineFollowerController.getInstance();

        autoModeRunner = AutoModeRunner.getInstance();
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
        groundIntakeLooper.start();
        driveTrain.zeroGyro();
        //get the enum for the selected automode
        Action currentAction = AutoModeSelector.getSelectedAction();
        //ask the factory to create an instance (if not already created)
        //use the instance to get direction and position
        robotStateCalculator.setStartDirection(AutoModeSelector.getStartDirection());
        robotStateCalculator.resetRobotState(AutoModeSelector.getStartingPos());
        //start running the auto mode
        autoModeRunner.start();
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
        //System.out.println("is auto done: " + autoModeRunner.isAutodone());

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
        groundIntakeLooper.start();
        driveTrain.zeroGyro();
        lineFollower.resetLineSensor();
        backLineFollower.resetLineSensor();
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
        groundIntakeLooper.stop();
        driveTrain.stop();
        autoModeRunner.setAction(AutoModeSelector.getSelectedAction());
    }

    private void driverControlled(){

        if (controls.getLightFollow()) {
            if (lineFollower != null && controls.getDriveTank() > 0 && lineFollower.getLineSensed()) {
                System.out.println("Front Sensors");
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(controls.getDriveTank()));
            } else if (backLineFollower != null && backLineFollower.getLineSensed()) {
                System.out.println("Back Sensors");
                driveTrain.drive(backLineFollower.getLightSensorMotorTurn(controls.getDriveTank()));
            }else if(visionController.isTarget()){
                driveTrain.drive(visionController.getMotorOutput(controls.getDriveTank()));
            } else {
                driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
            }
        } else {
            driveTrain.drive(driveHelper.drive(controls.getDriveTank(), controls.getDriveTurn(), controls.getQuickTurn()));
        }
        robotstate.outputToSmartDashboard();
        driveTrain.setHighGear(controls.getShift());
        //legClimberController.printEncoder();

        VisionController.showBackPiCamera(controls.getShowBackCamera());
        visionController.getValues();
        if (legClimberController != null) {
            //always pass the button for climb to the leg climber
            //it needs to keep track of how many times the button was pressed
            //pressed 10 times will allow us to climb even if the match isn't in its last 30 seconds
            if(controls.getClimberDown()){
                legClimberController.runClimber(LegClimberController.State.DOWN);
            }else if(controls.getClimberUp()){
                legClimberController.runClimber(LegClimberController.State.UP);
            }else{
                legClimberController.runClimber(LegClimberController.State.STOP);
            }
        }

        if(intake != null && groundIntake != null) {
            //System.out.println("INTAKES ARE NOT NULL");
            intake.setCargoIntake(controls.getCargoIntake());
            intake.toggleArmPosition(controls.getIntakeArmToggle());

            //shooting cargo
            if (controls.getCargoShoot() && controls.getRocket1Shoot()) {
                intake.setShootCargo(IntakeController.ShootSpeed.ROCKET1);
            } else if (controls.getCargoShoot() && controls.getRocket2Shoot()) {
                intake.setShootCargo(IntakeController.ShootSpeed.ROCKET2);
            } else if (controls.getCargoShoot()) {
                intake.setShootCargo(IntakeController.ShootSpeed.CARGOSHIP);
            }

            //hatches
            //if primary driver had pulled trigger to place a hatch on the front
//            if (controls.getHatchOuttake()) {
                intake.setHatchPlace(controls.getHatchOuttake());
//            } else {  //primary driver not holding front hatch trigger
                if (controls.getGroundIntakePlace()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.PLACEMENT);
                } else if (controls.getGroundIntakeReady()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.UP_CLOSED);
                } else if (controls.getGroundIntakeDown()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.DOWN_OPEN);
                } else if (controls.getGroundIntakeStarting()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.STARTING);
                }
//                if (!groundIntake.getIsPlacing()) {
//                    intake.setHatchPlace(false); //only close the jaws based on primary driver trigger if ground pickup not in the process of placing
//                }
//            }
        }
    }
}
