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
    private Timer driveTimer = new Timer();
    private double startValue = 0;
    private boolean wasClose = false;

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
        if(isForwards) {
            return visionController.isClose() || timer.get() - startValue > 3;
        }else{
            if((timer.get() - startValue < 6 && visionController.isClose()) || wasClose){
                wasClose = true;
                if(driveTimer.get() == 0){
                    driveTimer.start();
                }else if(driveTimer.get() < 1){
                    driveTrainController.drive(new DriveSignal(-.3,-.3));
                }else {
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;
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
        if(!isForwards) {
            visionController.showBackPiCamera(true);
        }else{
            visionController.showBackPiCamera(false);
        }
        if(visionController.getIsTarget()) {
            if(isForwards) {
                driveTrainController.drive(visionController.getMotorOutput(.4));
            }else {
                driveTrainController.drive(visionController.getMotorOutput(-.4));
            }
        }else{
            if(isForwards){
                driveTrainController.drive(new DriveSignal(.25, .25));
            }else {
                //driveTrain.drive(visionController.getMotorOutput(.5));
                driveTrainController.drive(new DriveSignal(-.25, -.25));
            }
        }
    }
}
