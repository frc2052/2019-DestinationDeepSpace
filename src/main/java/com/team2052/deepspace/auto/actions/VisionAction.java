package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.deepspace.subsystems.VisionController;
import com.team2052.lib.DriveSignal;
import edu.wpi.first.wpilibj.Timer;

public class VisionAction implements Action{

    private VisionController visionController= null;
    private DriveTrainController driveTrainController = null;
    private boolean isForwards;
    private Timer timer  = new Timer();
    private double startValue = 0;

    public VisionAction(boolean isForwards){
        this.isForwards = isForwards;
    }

    @Override
    public void done() {
        driveTrainController.stop();
    }

    @Override
    public boolean isFinished() {
        System.out.println("isclose: " + visionController.isClose());
        return visionController.isClose() || timer.get()-startValue > 4;
    }

    @Override
    public void start() {
        visionController = VisionController.getInstance();
        driveTrainController = DriveTrainController.getInstance();
        timer.start();
        startValue = timer.get();
        visionController.getValues();
    }

    @Override
    public void update() {
        if(visionController.getIsTarget()) {
            driveTrainController.drive(visionController.getMotorOutput(.5));
        }else{
            if(isForwards){
                driveTrainController.drive(new DriveSignal(.15, .15));
            }else {
                //driveTrain.drive(visionController.getMotorOutput(.5));
                driveTrainController.drive(new DriveSignal(-.15, -.15));
            }
        }
    }
}
