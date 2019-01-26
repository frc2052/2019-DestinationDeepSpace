package com.team2052.deepspace.auto.actions;

import com.team2052.deepspace.subsystems.DriveTrainController;
import com.team2052.deepspace.subsystems.VisionController;

public class VisionAction implements Action{

    VisionController visionController;
    DriveTrainController driveTrainController;
    @Override
    public void done() {

    }

    @Override
    public boolean isFinished() {
        return visionController.isClose();
    }

    @Override
    public void start() {
        visionController = VisionController.getInstance();
        driveTrainController = DriveTrainController.getInstance();
    }

    @Override
    public void update() {
        driveTrainController.drive(visionController.getMotorOutput());
    }
}
