package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.deepspace.subsystems.VisionController;
import com.team2052.lib.DriveSignal;

public class VisionAction implements Action{

    private VisionController visionController= null;
    private DriveTrainController driveTrainController = null;
    private boolean isForwards;

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
        return visionController.isClose();
    }

    @Override
    public void start() {
        visionController = VisionController.getInstance();
        driveTrainController = DriveTrainController.getInstance();
    }

    @Override
    public void update() {
        if(visionController.isTarget()) {
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
