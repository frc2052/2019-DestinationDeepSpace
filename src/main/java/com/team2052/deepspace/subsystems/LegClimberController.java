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

    private TalonSRX legClimberMotor = null;


    //with assistance
    private Solenoid LegClimberSolenoid1 = new Solenoid(Constants.LegClimber.kLegClimberSolenoid1id);

    private LegClimberController(){
        legClimberMotor = new TalonSRX(Constants.LegClimber.kLegClimberTalon1id);
        legClimberMotor.configFactoryDefault();
        legClimberMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, Constants.DriveTrain.kVelocityControlSlot, Constants.DriveTrain.kCANBusConfigTimeoutMS);
        legClimberMotor.setNeutralMode(NeutralMode.Brake);
        legClimberMotor.setInverted(false);
        legClimberMotor.setSensorPhase(true);
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

        //System.out.println("time: " + time );
       // if (time <= 40) {//todo: timer count up?
            if (on) {
                if(legClimberMotor.getSelectedSensorPosition() <= Constants.LegClimber.kClimberMotorDistance){
                    legClimberMotor.set(ControlMode.PercentOutput, 1);
                    System.out.println("RUNNING");
                }else {
                    stopClimber();
                }
                //with Assistance
                //LegClimberSolenoid1.set(true);

            }
 //       }
       // else if (legClimberButton == 4){
                //legClimberMotor.set(ControlMode.PercentOutput, Constants.LegClimber.kClimberMotorDistance);
                //with assistance
                //LegClimberSolenoid1.set(true);
   //     }
    }
    public void lowerClimber(){
        if (legClimberMotor.getSelectedSensorPosition() > 0){
            legClimberMotor.set(ControlMode.PercentOutput, -1);
            System.out.println("RUNNING BACK");
        } else{
            stopClimber();
        }

    }

    public void stopClimber(){
        legClimberMotor.set(ControlMode.PercentOutput, 0.0);
    }
    public void printEncoder(){
        System.out.println("climber pos: " + legClimberMotor.getSelectedSensorPosition());

    }

}
