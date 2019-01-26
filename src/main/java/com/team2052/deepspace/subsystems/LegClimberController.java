package com.team2052.deepspace.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.team2052.deepspace.Constants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;

public class LegClimberController {

    private static LegClimberController singleLegClimberControllerInstance = new LegClimberController();
    public static LegClimberController getInstance() { return singleLegClimberControllerInstance; }

    private TalonSRX legClimberMotor = new TalonSRX(Constants.Controls.kLegClimberTalon1id);
    private double pos = (Constants.LegClimber.kClimbMotorRotations * Constants.LegClimber.kEncoderTicksPerRotation);


    //with assistance
    private Solenoid LegClimberSolenoid1 = new Solenoid(Constants.Controls.kLegClimberSolenoid1id);

    private LegClimberController(){
        legClimberMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        legClimberMotor.setNeutralMode(NeutralMode.Coast);
    }

    public void resetEncoders() {
        legClimberMotor.setSelectedSensorPosition(0, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
    }

    private int legClimberButton = 0;
    private boolean isPressed = false;
    public void setLegClimber(boolean on) {
        double time = DriverStation.getInstance().getMatchTime();
        if (on && !isPressed){
            legClimberButton++;
        }

        //keep track of whether button is up or down
        isPressed = on;

        if (time <= 40) {
            if (on) {
                legClimberMotor.set(ControlMode.MotionMagic, pos);
                //with Assistance
                //LegClimberSolenoid1.set(true);

            }
        }
        else if (legClimberButton == 4){
                legClimberMotor.set(ControlMode.MotionMagic, pos);
                //with assistance
                //LegClimberSolenoid1.set(true);
        }
    }
    public void stopClimber(){
        legClimberMotor.set(ControlMode.MotionMagic, 0.0);
    }

}
