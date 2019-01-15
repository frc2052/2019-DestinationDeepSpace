package com.team2052.deepspace;

import com.team2052.deepspace.auto.AutoModeRunner;
import com.team2052.deepspace.auto.AutoModeSelector;
import com.team2052.deepspace.subsystems.DriveTrain;
import com.team2052.lib.ControlLoop;
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
    private DriveTrain driveTrain = DriveTrain.getInstance();
    private Controls controls = Controls.getInstance();
    private RobotState robotstate = RobotState.getInstance();
    private RobotStateCalculator robotStateCalculator = RobotStateCalculator.getInstance();
    private AutoModeRunner autoModeRunner = new AutoModeRunner();
    private ControlLoop controlLoop = new ControlLoop(Constants.Autonomous.kloopPeriodSec);
    private Compressor compressor = null;

    @Override
    public void robotInit() {
        controlLoop.addLoopable(robotStateCalculator);
        try {
            compressor = new Compressor();
            compressor.setClosedLoopControl(true);
        } catch (Exception exc) {
            System.out.println("DANGER: No compressor!");
        }

        AutoModeSelector.putToSmartDashboard();
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
        robotStateCalculator.resetRobotState();
        AutoModeSelector.AutoModeDefinition currentAutoMode = AutoModeSelector.getAutoDefinition();
        autoModeRunner.start(currentAutoMode.getInstance());
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        robotstate.outputToSmartDashboard();
    }

    /**
     This function called once when teleoperated starts
     */
    @Override
    public void teleopInit(){
        controlLoop.start();
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        driveTrain.drive(controls.getTankJoy1(), controls.getTurnJoy2());
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
    }
}
