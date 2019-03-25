package com.team2052.deepspace;

import com.team2052.deepspace.auto.AutoMode;
import com.team2052.deepspace.auto.AutoModeRunner;
import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.auto.PurePursuitPathFollower;
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
        backLineFollower = BackLineFollowerController.getInstance();

        autoModeRunner = AutoModeRunner.getInstance();
        try {
            compressor = new Compressor();
            compressor.setClosedLoopControl(true);
        } catch (Exception exc) {
            System.out.println("DANGER: No compressor!");
        }
        AutoModeSelector.putToShuffleBoard();

        AutoModeSelector.nullSelectedAutoMode();
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

        AutoMode currentMode = AutoModeSelector.getSelectedAutoMode();
        System.out.println("selected :" + currentMode.getClass().getName());
        //use the instance to get direction and position
        //todo: make one direction enum
        robotStateCalculator.setStartDirection(currentMode.getStartDirection().isForward);
        robotStateCalculator.resetRobotState(AutoModeSelector.getStartingPos());
        System.out.println("starting x: " + robotstate.getLatestPosition().getLateral() + " y: "+ robotstate.getLatestPosition().getForward());
        //start running the auto mode
        autoModeRunner.start(currentMode);
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
        //System.out.println("is auto done: " + autoModeRunner.isFinished());

        if(autoModeRunner.isFinished()){
            driverControlled();
        }
    }

    /**
     This function called once when teleoperated starts
     */
    @Override
    public void teleopInit(){
        AutoModeSelector.nullSelectedAutoMode();
        robotStateCalculator.resetRobotState();
        controlLoop.start();
        driveTrain.zeroGyro();
        if(lineFollower != null) {
            lineFollower.resetLineSensor();
        }

        if(backLineFollower != null) {
            backLineFollower.resetLineSensor();
        }

        if(legClimberController != null) {
            legClimberController.resetEncoders();
        }
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
        AutoModeSelector.checkSelectedAutoMode();
        PurePursuitPathFollower.getInstance().resetPathFollower();
    }

    private void driverControlled(){

        if (controls.getLightFollow()) {
            /*if (lineFollower != null && controls.getDriveTank() > 0 && lineFollower.getLineSensed()) {
                System.out.println("Front Sensors");
                driveTrain.drive(lineFollower.getLightSensorMotorTurn(controls.getDriveTank()));
            } else if (backLineFollower != null && backLineFollower.getLineSensed()) {
                System.out.println("Back Sensors");
                driveTrain.drive(backLineFollower.getLightSensorMotorTurn(controls.getDriveTank()));
            }else */if(visionController.isTarget()){
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
            if(controls.getClimberOverride()){
                if(controls.getClimberDown()){
                    legClimberController.runClimber(LegClimberController.State.OVERRIDEDOWN);
                }else if(controls.getClimberUp()){
                    legClimberController.runClimber(LegClimberController.State.OVERRIDEUP);
                }else{
                    legClimberController.runClimber(LegClimberController.State.STOP);
                }
            }else if(controls.getClimberDown()){
                legClimberController.runClimber(LegClimberController.State.DOWN);
            }else if(controls.getClimberUp()){
                legClimberController.runClimber(LegClimberController.State.UP);
            }else{
                legClimberController.runClimber(LegClimberController.State.STOP);
            }
        }
        legClimberController.printEncoder();

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
            } else if(!controls.getCargoIntake()){
                //only stop motors if we're not doing cargo intake
                intake.setShootCargo(IntakeController.ShootSpeed.NONE);
            }

            //hatches
            //if primary driver had pulled trigger to place a hatch on the front
//            if (controls.getHatchOuttake()) {
                intake.setHatchPlace(controls.getHatchOuttake());
//            } else {  //primary driver not holding front hatch trigger
                if (controls.getGroundIntakePlace()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.PLACEMENT);
                } else if (controls.getGroundIntakeReady()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.READY);
                } else if (controls.getGroundIntakeDown()) {
                    groundIntake.setWantState(GroundIntakeController.IntakeState.DOWN);
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
