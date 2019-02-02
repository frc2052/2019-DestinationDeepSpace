package com.team2052.deepspace;

import com.team2052.deepspace.subsystems.IntakeController;
import com.team2052.deepspace.subsystems.LegClimberController;
import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.deepspace.subsystems.ElevatorController;
import com.team2052.deepspace.subsystems.GroundIntake;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private IntakeController intake = null;
    private Controls controls = null;
    private DriveTrainController driveTrain = null;
    private ElevatorController elevator = null;
    private GroundIntake groundIntake;
    private LegClimberController legClimberController = null;




    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        intake = IntakeController.getInstance();
        controls = Controls.getInstance();
        legClimberController = LegClimberController.getInstance();
        legClimberController.resetEncoders();
        driveTrain = DriveTrainController.getInstance();
        elevator = ElevatorController.getInstance();
        elevator.zeroSensor();
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
        groundIntake.update();
    }

    /**
        This function called once when autonomous starts
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     This function called once when teleoperated starts
     */
    @Override
    public void teleopInit(){


    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        if (controls.legClimber()){
            legClimberController.setLegClimber(controls.legClimber());
        } else if (controls.lowerClimber()){
            legClimberController.lowerClimber();
        }else {
            legClimberController.stopClimber();
        }

        if(controls.getIntake()){
            intake.cargoIntake();

        } else if (controls.getOuttake()) {
            intake.cargoOuttake();
        } else {
            intake.cargoNeutral();
        }

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
    }



    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}
